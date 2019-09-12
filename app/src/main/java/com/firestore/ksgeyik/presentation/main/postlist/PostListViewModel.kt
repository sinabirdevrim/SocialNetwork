package com.firestore.ksgeyik.presentation.main.postlist

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.firestore.android.repository.DataManager
import com.firestore.android.repository.model.Post
import com.firestore.ksgeyik.common.BaseViewModel
import com.firestore.ksgeyik.enums.ViewState

class PostListViewModel(dataManager: DataManager) : BaseViewModel() {

    var dataManager = dataManager
    var liveData = MutableLiveData<List<Post>>()
    var viewState = ObservableField(ViewState.LOADING)

    fun getPostList() {
        dataManager.getFireStoreManager()?.getPosts()?.addOnSuccessListener {
            val postList = it?.toObjects(Post::class.java)
            liveData.postValue(postList)
            postList?.let {
                when (postList.size) {
                    0 -> viewState.set(ViewState.EMPTY)
                    else -> viewState.set(ViewState.CONTENT)
                }
            } ?: run {
                viewState.set(ViewState.ERROR)
            }
        }?.addOnFailureListener {
            viewState.set(ViewState.ERROR)
        }
    }
}