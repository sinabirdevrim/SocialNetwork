package com.firestore.ksgeyik.di

import com.firestore.ksgeyik.presentation.SplashActivity
import com.firestore.ksgeyik.presentation.login.LoginActivity
import com.firestore.ksgeyik.presentation.main.MainActivity
import com.firestore.ksgeyik.presentation.main.MainActivityModule
import com.firestore.ksgeyik.presentation.register.RegisterActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 *      Daggerın ihtiyacı olan sınıf, MainActivity nin inject edilmesi gerektiğini anlıyor
 *      Artık component yazmıyoruz ContributesAndroidInjector hangi modülü nereye verecek anlatıyor
 */

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun buildMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun buildRegisterActivity(): RegisterActivity

    @ContributesAndroidInjector
    abstract fun buildLoginActivity(): LoginActivity

    @ContributesAndroidInjector
    abstract fun buildSplashActivity(): SplashActivity

}