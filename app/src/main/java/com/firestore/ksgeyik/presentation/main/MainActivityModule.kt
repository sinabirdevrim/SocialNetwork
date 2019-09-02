package com.firestore.ksgeyik.presentation.main

import com.firestore.ksgeyik.presentation.main.postlist.PostListFragment
import com.firestore.ksgeyik.presentation.main.postshare.PostShareFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector
    abstract fun providePostListFragment(): PostListFragment

    @ContributesAndroidInjector
    abstract fun providePostShareFragment(): PostShareFragment
}