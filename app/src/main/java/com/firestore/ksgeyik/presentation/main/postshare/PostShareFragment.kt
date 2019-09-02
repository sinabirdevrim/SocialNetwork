package com.firestore.ksgeyik.presentation.main.postshare

import androidx.databinding.library.baseAdapters.BR
import com.firestore.ksgeyik.R
import com.firestore.ksgeyik.common.BaseFragment
import com.firestore.ksgeyik.databinding.FragmentPostShareBinding

class PostShareFragment : BaseFragment<FragmentPostShareBinding, PostShareViewModel>() {

    companion object {
        @JvmStatic
        fun newInstance(): PostShareFragment {
            return PostShareFragment()
        }
    }

    override fun getViewModelBindingVariable(): Int {
        return BR.viewmodel
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_post_share
    }

}