package com.firestore.ksgeyik.presentation.main.postlist

import androidx.lifecycle.MutableLiveData
import com.firestore.android.repository.DataManager
import com.firestore.android.repository.model.Post
import com.firestore.ksgeyik.common.BaseViewModel

class PostListViewModel(dataManager: DataManager) : BaseViewModel() {

    var dataManager = dataManager
    var liveData = MutableLiveData<List<Post>>()

    fun getPostList() {
        dataManager.getFireStoreManager()?.getPosts()?.addOnCompleteListener {
            val postList = it.result?.toObjects(Post::class.java)
            liveData.postValue(postList)
        }
    }
}