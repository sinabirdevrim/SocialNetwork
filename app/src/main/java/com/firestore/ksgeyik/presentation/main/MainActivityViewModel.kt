package com.firestore.ksgeyik.presentation.main

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.firestore.android.repository.DataManager
import com.firestore.android.repository.model.User
import com.firestore.ksgeyik.common.BaseViewModel
import com.firestore.ksgeyik.common.Constants
import com.firestore.ksgeyik.enums.ViewState
import com.orhanobut.hawk.Hawk

class MainActivityViewModel(dataManager: DataManager) : BaseViewModel() {

    val dataManager = dataManager
    val liveData = MutableLiveData<User>()
    var url = ObservableField<String>()
    var viewState = ObservableField(ViewState.LOADING)

    fun getUser() {

        var disposable = dataManager?.getFireStoreManager()?.getUser(Hawk.get(Constants.USER_ID))
            ?.addOnCompleteListener {
                val user = it?.result?.toObject(User::class.java)
                liveData.postValue(user)
                url.set(user?.photoUrl)
                viewState.set(ViewState.CONTENT)
            }

    }

}