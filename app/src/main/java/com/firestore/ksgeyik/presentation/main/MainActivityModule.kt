package com.firestore.ksgeyik.presentation.main

import com.firestore.ksgeyik.presentation.main.postlist.PostListFragment
import com.firestore.ksgeyik.presentation.main.postshare.PostShareFragment
import com.firestore.ksgeyik.presentation.main.search.SearchFragment
import com.firestore.ksgeyik.presentation.main.search.SettingsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector
    abstract fun providePostListFragment(): PostListFragment

    @ContributesAndroidInjector
    abstract fun providePostShareFragment(): PostShareFragment

    @ContributesAndroidInjector
    abstract fun provideSearchFragment(): SearchFragment

    @ContributesAndroidInjector
    abstract fun provideSettingsFragment(): SettingsFragment
}