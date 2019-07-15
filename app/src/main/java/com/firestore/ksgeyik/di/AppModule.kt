package com.firestore.ksgeyik.di

import com.firestore.android.repository.DataManager
import com.firestore.ksgeyik.util.SchedulerProvider
import com.firestore.ksgeyik.util.ViewModelProviderFactory
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


@Module
class AppModule {

    @Provides
    fun provideGson() = Gson()

    @Provides
    fun dataManager() = DataManager()

    @Provides
    fun provideSchedulerProvider() = SchedulerProvider(Schedulers.io(), AndroidSchedulers.mainThread())

    @Provides
    fun provideViewModelFactory() = ViewModelProviderFactory(dataManager(), provideSchedulerProvider())

}