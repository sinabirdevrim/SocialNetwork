package com.firestore.ksgeyik.presentation.register

import android.net.Uri
import androidx.databinding.ObservableField
import com.firestore.android.repository.DataManager
import com.firestore.android.repository.model.User
import com.firestore.ksgeyik.common.BaseViewModel
import com.firestore.ksgeyik.common.Constants
import com.firestore.ksgeyik.common.SingleLiveEvent
import com.firestore.ksgeyik.enums.ViewState
import com.firestore.ksgeyik.util.SchedulerProvider
import com.orhanobut.hawk.Hawk

class RegisterViewModel(dataManager: DataManager?, schedulerProvider: SchedulerProvider) :
    BaseViewModel() {

    var liveData = SingleLiveEvent<Boolean>()
    var viewState = ObservableField<ViewState>(ViewState.EMPTY)
    var dataManager: DataManager? = dataManager


    fun savePhoto(fileUri: Uri?, user: User) {
        viewState.set(ViewState.LOADING)
        fileUri?.let {
            savePhotoAndUser(fileUri, user)
        } ?: run {
            saveUser(user)
        }

    }

    private fun savePhotoAndUser(fileUri: Uri?, user: User) {
        dataManager?.getFireStorageManager()?.putFile(fileUri!!)?.addOnSuccessListener {
            it.metadata!!.reference!!.downloadUrl.addOnSuccessListener {
                //set photoUrl after upload
                user.photoUrl = it.toString()
                saveUser(user)
            }
        }?.addOnFailureListener {
            viewState.set(ViewState.ERROR)
            liveData.postValue(false)
        }
    }

    fun saveUser(user: User) {
        dataManager?.getFireStoreManager()?.saveUser(user)
            ?.addOnSuccessListener { documentReference ->
                viewState.set(ViewState.CONTENT)
                liveData.postValue(true)
                putHawk(documentReference.id, user)
            }?.addOnFailureListener {
                viewState.set(ViewState.ERROR)
                liveData.postValue(false)
            }
    }

    private fun putHawk(id: String, user: User) {
        Hawk.put(Constants.IS_LOGIN, true)
        Hawk.put(Constants.USER_ID, id)
        Hawk.put(Constants.USER, user)
    }

}