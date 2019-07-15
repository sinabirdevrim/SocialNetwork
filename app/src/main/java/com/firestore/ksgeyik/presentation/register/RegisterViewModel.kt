package com.firestore.ksgeyik.presentation.register

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.firestore.android.repository.DataManager
import com.firestore.android.repository.model.User
import com.firestore.ksgeyik.common.BaseViewModel
import com.firestore.ksgeyik.common.Constants
import com.firestore.ksgeyik.enums.ViewState
import com.firestore.ksgeyik.util.SchedulerProvider
import com.orhanobut.hawk.Hawk

class RegisterViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) : BaseViewModel() {

    var liveData: MutableLiveData<Boolean>
    var viewState: ObservableField<ViewState>
    var dataManager: DataManager = dataManager

    init {
        viewState = ObservableField(ViewState.EMPTY)
        liveData = MutableLiveData()
    }

    fun saveUser(user: User) {
        viewState.set(ViewState.LOADING)
        dataManager?.getFireStoreManager()?.saveUser(user)?.addOnSuccessListener { documentReference ->
            viewState.set(ViewState.CONTENT)
            liveData.postValue(true)
            putHawk(documentReference.id)
        }?.addOnFailureListener {
            viewState.set(ViewState.ERROR)
            liveData.postValue(false)
        }
    }

    private fun putHawk(id: String) {
        Hawk.put(Constants.IS_LOGIN, true)
        Hawk.put(Constants.USER, id)
    }

}