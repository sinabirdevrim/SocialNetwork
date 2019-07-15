package com.firestore.ksgeyik.presentation

import android.os.Bundle
import com.firestore.ksgeyik.R
import com.firestore.ksgeyik.common.Constants
import com.firestore.ksgeyik.presentation.login.LoginActivity
import com.firestore.ksgeyik.presentation.main.MainActivity
import com.orhanobut.hawk.Hawk
import dagger.android.DaggerActivity
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask

class SplashActivity : DaggerActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initIfNull()
        start()
    }

    private fun start() {
        when (Hawk.get<Boolean>(Constants.IS_LOGIN)) {
            true -> startActivity(intentFor<MainActivity>().clearTop().newTask())
            false -> startActivity(intentFor<LoginActivity>().clearTop().newTask())
        }
        finish()
    }

    private fun initIfNull() {
        Hawk.get<Boolean>(Constants.IS_LOGIN) ?: Hawk.put(Constants.IS_LOGIN, false)
    }
}