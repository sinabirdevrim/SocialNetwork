package com.firestore.ksgeyik.presentation.login

import android.os.Bundle
import androidx.databinding.library.baseAdapters.BR
import com.firestore.ksgeyik.common.BaseActivity
import com.firestore.ksgeyik.databinding.ActivityLoginBinding
import com.firestore.ksgeyik.presentation.main.MainActivity
import com.firestore.ksgeyik.presentation.register.RegisterActivity
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask


class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewDataBinding?.activityLoginSignUpBtn?.setOnClickListener {
            startActivity(intentFor<RegisterActivity>())
        }

        mViewDataBinding?.activityLoginSignInBtn?.setOnClickListener {
            startActivity(intentFor<MainActivity>().clearTop().newTask())
            finish()
        }
    }

    override fun getLayoutId(): Int {
        return com.firestore.ksgeyik.R.layout.activity_login
    }

    override fun getViewModelBindingVariable(): Int {
        return BR.viewmodel
    }
}