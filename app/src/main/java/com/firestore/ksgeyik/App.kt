package com.firestore.ksgeyik

import com.firestore.ksgeyik.di.DaggerAppComponent
import com.orhanobut.hawk.Hawk
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class App : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        Hawk.init(this).build()
    }

    /**
     *      out = DaggerApplicationdan extend olan sınıflarıda kullanabilirsin
     */
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this)
        return appComponent
    }

}