package com.firestore.ksgeyik.presentation.register

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.firestore.android.repository.model.User
import com.firestore.ksgeyik.R
import com.firestore.ksgeyik.common.BaseActivity
import com.firestore.ksgeyik.databinding.ActivityRegisterBinding
import com.firestore.ksgeyik.presentation.main.MainActivity
import com.firestore.ksgeyik.util.Utils
import com.theartofdev.edmodo.cropper.CropImage
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import org.jetbrains.anko.toast


class RegisterActivity : BaseActivity<ActivityRegisterBinding, RegisterViewModel>() {

    lateinit var fileUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewDataBinding?.apply {
            activityRegisterPicIv.setOnClickListener {
                Utils.openCamera(this@RegisterActivity)
            }
        }

        mViewDataBinding?.apply {
            activityRegisterSignUpBtn.setOnClickListener {
                mViewModel.savePhoto(
                    fileUri, User(
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
                false ->toast(getString(R.string.error))
            }
            finish()
        })

    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == Activity.RESULT_OK) {
                fileUri = result.uri
                Glide.with(this).load(fileUri).apply(RequestOptions.circleCropTransform()).into(activityRegisterPicIv)
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error = result.error
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_register
    }

    override fun getViewModelBindingVariable(): Int {
        return BR.viewmodel
    }

}