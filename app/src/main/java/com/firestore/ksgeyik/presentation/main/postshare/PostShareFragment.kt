package com.firestore.ksgeyik.presentation.main.postshare

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.firestore.android.repository.model.Post
import com.firestore.ksgeyik.R
import com.firestore.ksgeyik.common.BaseFragment
import com.firestore.ksgeyik.common.Constants
import com.firestore.ksgeyik.databinding.FragmentPostShareBinding
import com.firestore.ksgeyik.enums.ToolBarState
import com.firestore.ksgeyik.extensions.hideSoftKeyboard
import com.firestore.ksgeyik.extensions.showSoftKeyboard
import com.firestore.ksgeyik.presentation.main.MainActivity
import com.firestore.ksgeyik.util.Utils
import com.orhanobut.hawk.Hawk
import com.theartofdev.edmodo.cropper.CropImage
import org.jetbrains.anko.support.v4.toast

class PostShareFragment : BaseFragment<FragmentPostShareBinding, PostShareViewModel>() {

    private var fileUri: Uri? = null

    companion object {
        @JvmStatic
        fun newInstance(): PostShareFragment {
            return PostShareFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).getShareButton()?.setOnClickListener {
            val text = mViewDataBinding?.activityPostShareEt?.text?.toString()?.trim()
            if (text?.isNotEmpty()!!) {
                val post = Post("", text, 1, "", Hawk.get(Constants.USER), false)
                fileUri?.let {
                    mViewModel.sharePost(post, fileUri)
                } ?: run {
                    mViewModel.sharePost(post, null)
                }
            } else {
                toast(getString(R.string.empty_post))
            }
        }

        mViewModel.liveData.observe(this, Observer {
            when (it) {
                true -> (activity as MainActivity).onBackPressed()
                false -> toast(getString(R.string.error))
            }
        })

        /**
         * focus keyboard
         */
        showKeyboard()
        mViewDataBinding?.activityPostShareGalleryIv?.setOnClickListener {
            Utils.openCamera(activity as MainActivity)
        }
    }

    private fun showKeyboard() {
        showSoftKeyboard(mViewDataBinding?.activityPostShareEt!!)
        mViewDataBinding?.activityPostShareIv?.setOnClickListener {
            showSoftKeyboard(
                mViewDataBinding?.activityPostShareEt!!
            )
        }
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == Activity.RESULT_OK) {
                fileUri = result.uri
                Glide.with(this).load(fileUri).into(mViewDataBinding?.activityPostShareIv!!)
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error = result.error
            }
        }
    }

    override fun onPause() {
        hideSoftKeyboard()
        super.onPause()
    }

    override fun getViewModelBindingVariable(): Int {
        return BR.viewmodel
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_post_share
    }

    override fun setToolbarState() {
        (activity as MainActivity).setToolbar(ToolBarState.POSTSHARE)
    }
}