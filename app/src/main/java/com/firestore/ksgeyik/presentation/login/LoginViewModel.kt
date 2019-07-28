package com.firestore.ksgeyik.presentation.login

import com.firestore.android.repository.DataManager
import com.firestore.android.repository.model.User
import com.firestore.ksgeyik.common.BaseViewModel
import com.firestore.ksgeyik.common.SingleLiveEvent

class LoginViewModel(dataManager: DataManager) : BaseViewModel() {

    var dataManager = dataManager
    var liveData = SingleLiveEvent<Boolean>()

    fun getUser(email: String, password: String) {
        dataManager.getFireStoreManager()?.getUserByUserNameAndPassWord(email,password)?.addOnCompleteListener {
            liveData.postValue(true)
        }?.addOnFailureListener {
            liveData.postValue(false)
        }


    }
}