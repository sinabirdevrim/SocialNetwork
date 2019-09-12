package com.firestore.ksgeyik.presentation.main

import androidx.databinding.ObservableField
import com.firestore.android.repository.DataManager
import com.firestore.android.repository.model.User
import com.firestore.ksgeyik.common.BaseViewModel
import com.firestore.ksgeyik.common.SingleLiveEvent
import com.firestore.ksgeyik.enums.ToolBarState

class MainActivityViewModel(dataManager: DataManager) : BaseViewModel() {

    val dataManager = dataManager
    val liveData = SingleLiveEvent<User>()
    var url = ObservableField<String>()
    var toolBarState = ObservableField<ToolBarState>()

    fun setToolbarImage(photoUrl: String?) {
        url.set(photoUrl)
    }
}