package com.firestore.ksgeyik.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.firestore.android.repository.DataManager
import com.firestore.ksgeyik.presentation.login.LoginViewModel
import com.firestore.ksgeyik.presentation.main.MainActivityViewModel
import com.firestore.ksgeyik.presentation.main.postlist.PostListViewModel
import com.firestore.ksgeyik.presentation.main.postshare.PostShareViewModel
import com.firestore.ksgeyik.presentation.main.search.SearchViewModel
import com.firestore.ksgeyik.presentation.main.search.SettingsViewModel
import com.firestore.ksgeyik.presentation.register.RegisterViewModel
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ViewModelProviderFactory @Inject constructor(private val dataManager: DataManager, private val schedulerProvider: SchedulerProvider
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java!!) -> LoginViewModel(dataManager) as T
            modelClass.isAssignableFrom(RegisterViewModel::class.java!!) -> RegisterViewModel(dataManager,schedulerProvider) as T
            modelClass.isAssignableFrom(MainActivityViewModel::class.java!!) -> MainActivityViewModel(dataManager) as T
            modelClass.isAssignableFrom(PostListViewModel::class.java!!) -> PostListViewModel(dataManager) as T
            modelClass.isAssignableFrom(PostShareViewModel::class.java!!) -> PostShareViewModel(dataManager) as T
            modelClass.isAssignableFrom(SearchViewModel::class.java!!) -> SearchViewModel(dataManager) as T
            modelClass.isAssignableFrom(SettingsViewModel::class.java!!) -> SettingsViewModel(dataManager) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}