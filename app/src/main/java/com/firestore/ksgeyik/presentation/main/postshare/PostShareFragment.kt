package com.firestore.ksgeyik.presentation.main.postshare

import android.os.Bundle
import android.view.View
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import com.firestore.android.repository.model.Post
import com.firestore.ksgeyik.R
import com.firestore.ksgeyik.common.BaseFragment
import com.firestore.ksgeyik.common.Constants
import com.firestore.ksgeyik.databinding.FragmentPostShareBinding
import com.firestore.ksgeyik.enums.ToolBarState
import com.firestore.ksgeyik.presentation.main.MainActivity
import com.orhanobut.hawk.Hawk
import org.jetbrains.anko.support.v4.toast

class PostShareFragment : BaseFragment<FragmentPostShareBinding, PostShareViewModel>() {

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
                val post = Post("", text, 1, "", Hawk.get(Constants.USER))
                mViewModel.sharePost(post)
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