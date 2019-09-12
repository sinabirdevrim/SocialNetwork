package com.firestore.ksgeyik.presentation.main.postshare

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

    fun sharePost(post: Post) {
        viewState.set(ViewState.LOADING)
        dataManager?.getFireStoreManager()?.savePost(post)?.addOnSuccessListener {
            liveData.postValue(true)
            viewState.set(ViewState.CONTENT)
        }?.addOnFailureListener {
            liveData.postValue(false)
            viewState.set(ViewState.ERROR)
        }
    }

}