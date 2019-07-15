package com.firestore.ksgeyik.presentation.main

import com.firestore.ksgeyik.presentation.main.postlist.PostListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector
    abstract fun providePostListFragment(): PostListFragment
}