package com.firestore.ksgeyik.presentation.login

import android.os.Bundle
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import com.firestore.ksgeyik.R
import com.firestore.ksgeyik.common.BaseActivity
import com.firestore.ksgeyik.databinding.ActivityLoginBinding
import com.firestore.ksgeyik.presentation.main.MainActivity
import com.firestore.ksgeyik.presentation.register.RegisterActivity
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import org.jetbrains.anko.toast


class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signUpClickListener()
        signInClickListener()
        observeResult()
    }

    private fun signUpClickListener() {
        mViewDataBinding?.activityLoginSignUpBtn?.setOnClickListener {
            startActivity(intentFor<RegisterActivity>())
        }
    }

    private fun signInClickListener() {
        mViewDataBinding?.activityLoginSignInBtn?.setOnClickListener {
            val email = mViewDataBinding?.activityLoginEmailEt?.text.toString()
            val passWord = mViewDataBinding?.activityLoginPasswordEt?.text.toString()
            mViewModel.login(email, passWord)
        }
    }

    private fun observeResult() {
        mViewModel.liveData.observe(this, Observer {
            when (it) {
                true -> {
                    startActivity(intentFor<MainActivity>().clearTop().newTask())
                    finish()
                }
                false -> toast(getString(R.string.login_failure))
            }
        })
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun getViewModelBindingVariable(): Int {
        return BR.viewmodel
    }
}