package com.firestore.ksgeyik.presentation.main

import androidx.lifecycle.MutableLiveData
import com.firestore.android.repository.DataManager
import com.firestore.android.repository.model.User
import com.firestore.ksgeyik.common.BaseViewModel
import com.firestore.ksgeyik.common.Constants
import com.orhanobut.hawk.Hawk

class MainActivityViewModel(dataManager: DataManager) : BaseViewModel() {

    val dataManager = dataManager
    val liveData = MutableLiveData<User>()

    fun getUser() {
        dataManager?.getFireStoreManager()?.getUser(Hawk.get(Constants.USER_ID))?.addOnCompleteListener {
            val user = it?.result?.toObject(User::class.java)
            liveData.postValue(user)
        }
    }

}