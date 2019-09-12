package com.firestore.ksgeyik.presentation.login

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.firestore.android.repository.DataManager
import com.firestore.android.repository.model.User
import com.firestore.ksgeyik.common.BaseViewModel
import com.firestore.ksgeyik.common.Constants
import com.firestore.ksgeyik.enums.ViewState
import com.orhanobut.hawk.Hawk

class LoginViewModel(dataManager: DataManager?) : BaseViewModel() {

    var liveData: MutableLiveData<Boolean>
    var viewState: ObservableField<ViewState>
    var dataManager: DataManager? = dataManager

    init {
        viewState = ObservableField(ViewState.EMPTY)
        liveData = MutableLiveData()
    }

    fun login(email: String, password: String) {
        dataManager?.getFireStoreManager()?.login(email, password)?.get()?.addOnSuccessListener {
            if (it.documents.size > 0) {
                val data = it.documents[0].toObject(User::class.java)
                viewState.set(ViewState.CONTENT)
                liveData.postValue(true)
                putHawk(it.documents[0].id,data)
            } else {
                viewState.set(ViewState.ERROR)
                liveData.postValue(false)
            }
        }?.addOnFailureListener {
            viewState.set(ViewState.ERROR)
            liveData.postValue(false)
        }
    }

    private fun putHawk(id: String, user: User?) {
        Hawk.put(Constants.IS_LOGIN, true)
        Hawk.put(Constants.USER_ID, id)
        Hawk.put(Constants.USER, user)
    }
}