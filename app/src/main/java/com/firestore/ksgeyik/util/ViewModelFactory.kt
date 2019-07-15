package com.firestore.ksgeyik.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.firestore.android.repository.DataManager
import com.firestore.ksgeyik.presentation.login.LoginViewModel
import com.firestore.ksgeyik.presentation.main.MainActivityViewModel
import com.firestore.ksgeyik.presentation.main.postlist.PostListViewModel
import com.firestore.ksgeyik.presentation.register.RegisterViewModel
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ViewModelProviderFactory @Inject constructor(private val dataManager: DataManager, private val schedulerProvider: SchedulerProvider
) : ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(LoginViewModel::class.java!!) -> return LoginViewModel() as T
            modelClass.isAssignableFrom(RegisterViewModel::class.java!!) -> return RegisterViewModel(dataManager,schedulerProvider) as T
            modelClass.isAssignableFrom(MainActivityViewModel::class.java!!) -> return MainActivityViewModel() as T
            modelClass.isAssignableFrom(PostListViewModel::class.java!!) -> return PostListViewModel(dataManager) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name) as Throwable
        }


    }
}