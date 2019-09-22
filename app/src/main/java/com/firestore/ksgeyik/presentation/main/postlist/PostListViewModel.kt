package com.firestore.ksgeyik.presentation.main.postlist

import androidx.databinding.ObservableField
import androidx.lifecycle.LifecycleObserver
import com.firestore.android.repository.DataManager
import com.firestore.android.repository.model.User
import com.firestore.ksgeyik.common.BaseViewModel
import com.firestore.ksgeyik.common.Constants
import com.firestore.ksgeyik.common.SingleLiveEvent
import com.firestore.ksgeyik.data.model.Post
import com.firestore.ksgeyik.enums.ViewState
import com.google.firebase.firestore.QuerySnapshot
import com.orhanobut.hawk.Hawk

class PostListViewModel(dataManager: DataManager) : BaseViewModel(), LifecycleObserver {

    var dataManager = dataManager
    var liveData = SingleLiveEvent<MutableList<Post?>>()
    var viewState = ObservableField(ViewState.LOADING)

    // @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun getPostList() {
        dataManager.getFireStoreManager()?.getPosts()?.addOnSuccessListener {
            var postList = createList(it)
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

    private fun createList(it: QuerySnapshot): MutableList<Post?>? {
        var postList = mutableListOf<Post?>()
        for (document in it.documents) {
            val post: Post? = document.toObject(Post::class.java)
            post?.id = document.id
            postList.add(post)
        }
        return postList
    }

    fun likeOrUnlikePost(post: Post?) {
        val isLiked: Boolean? = post?.likeList?.contains(Hawk.get<User>(Constants.USER))
        isLiked?.let {
            when (isLiked) {
                true -> likePost(post)
                false -> unlikePost(post)
            }
        } ?: run {
            likePost(post)
        }
    }

    private fun likePost(post: Post?) {
        var likeList = post?.likeList
        likeList?.let {
            post?.likeList?.add(Hawk.get(Constants.USER))
        } ?: run {
            likeList = mutableListOf<User>()
            likeList!!.add(Hawk.get(Constants.USER))
            post?.likeList = likeList
        }
        dataManager.getFireStoreManager()?.likeOrUnlikePost(post)?.addOnSuccessListener {

        }?.addOnFailureListener {

        }
    }

    private fun unlikePost(post: Post?) {
        post?.likeList?.remove(Hawk.get(Constants.USER))
        dataManager.getFireStoreManager()?.likeOrUnlikePost(post)?.addOnSuccessListener {
        }?.addOnFailureListener {

        }
    }

    fun commentPost(post: Post) {
        dataManager.getFireStoreManager()?.getPosts()?.addOnSuccessListener {
        }?.addOnFailureListener {
        }
    }
}