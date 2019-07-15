package com.firestore.ksgeyik.presentation.register

import android.os.Bundle
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import com.firestore.android.repository.model.User
import com.firestore.ksgeyik.R
import com.firestore.ksgeyik.common.BaseActivity
import com.firestore.ksgeyik.databinding.ActivityRegisterBinding
import com.firestore.ksgeyik.presentation.main.MainActivity
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask


class RegisterActivity : BaseActivity<ActivityRegisterBinding, RegisterViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewDataBinding?.apply {
            activityRegisterSignUpBtn.setOnClickListener {
                mViewModel.saveUser(
                    User(
                        activityRegisterNameEt.text.toString(),
                        activityRegisterSurnameEt.text.toString(),
                        activityRegisterEmailEt.text.toString(),
                        activityRegisterPasswordEt.text.toString(),
                        ""
                    )
                )
            }
        }

        mViewModel.liveData.observe(this, Observer {
            when (it) {
                true -> startActivity(intentFor<MainActivity>().clearTop().newTask())
            }
            finish()
        })

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_register
    }

    override fun getViewModelBindingVariable(): Int {
        return BR.viewmodel
    }

}