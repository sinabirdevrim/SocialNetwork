package com.firestore.ksgeyik.presentation.main.postshare

import android.net.Uri
import androidx.databinding.ObservableField
import com.firestore.android.repository.DataManager
import com.firestore.android.repository.model.Post
import com.firestore.ksgeyik.common.BaseViewModel
import com.firestore.ksgeyik.common.SingleLiveEvent
import com.firestore.ksgeyik.enums.ViewState

class PostShareViewModel(dataManager: DataManager?) : BaseViewModel() {

    var dataManager = dataManager
    var liveData = SingleLiveEvent<Boolean>()
    var viewState = ObservableField(ViewState.EMPTY)

    fun sharePost(post: Post, uri: Uri?) {
        viewState.set(ViewState.LOADING)
        uri?.let {
            savePhoto(post, uri)
        } ?: run {
            savePost(post)
        }
    }

    private fun savePost(post: Post) {
        dataManager?.getFireStoreManager()?.savePost(post)?.addOnSuccessListener {
            liveData.postValue(true)
            viewState.set(ViewState.CONTENT)
        }?.addOnFailureListener {
            liveData.postValue(false)
            viewState.set(ViewState.ERROR)
        }
    }

    private fun savePhoto(post: Post, uri: Uri) {
        dataManager?.getFireStorageManager()?.putFile(uri)?.addOnSuccessListener {
            it.metadata!!.reference!!.downloadUrl.addOnSuccessListener {
                post.contentPhotoUrl = it.toString()
                savePost(post)
            }
        }?.addOnFailureListener {
            viewState.set(ViewState.ERROR)
            liveData.postValue(false)
        }
    }

}