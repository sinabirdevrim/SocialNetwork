package com.firestore.ksgeyik.presentation.main.postshare

import com.firestore.android.repository.DataManager
import com.firestore.android.repository.model.Post
import com.firestore.ksgeyik.common.BaseViewModel
import com.firestore.ksgeyik.common.Constants
import com.firestore.ksgeyik.common.SingleLiveEvent
import com.orhanobut.hawk.Hawk

class PostShareViewModel(dataManager: DataManager) : BaseViewModel() {
    var dataManager = dataManager
    var liveData = SingleLiveEvent<Boolean>()

    fun sharePost(text: String) {
        val post = Post("title", text, 0, "", Hawk.get(Constants.USER))
        val savingListener = dataManager.getFireStoreManager()?.savePost(post)
        savingListener?.addOnSuccessListener { liveData.postValue(true) }
            ?.addOnFailureListener { liveData.postValue(false) }
    }
}