package com.firestore.ksgeyik.presentation.main.postlist

import androidx.databinding.ObservableField
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.firestore.android.repository.DataManager
import com.firestore.ksgeyik.common.BaseViewModel
import com.firestore.ksgeyik.common.SingleLiveEvent
import com.firestore.ksgeyik.data.model.Post
import com.firestore.ksgeyik.enums.ViewState

class PostListViewModel(dataManager: DataManager) : BaseViewModel(), LifecycleObserver {

    var dataManager = dataManager
    var liveData = SingleLiveEvent<List<Post>>()
    var viewState = ObservableField(ViewState.LOADING)

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
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