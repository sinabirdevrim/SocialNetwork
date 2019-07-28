package com.firestore.ksgeyik.presentation.main.postshare

import android.os.Bundle
import android.view.View
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import com.firestore.ksgeyik.R
import com.firestore.ksgeyik.common.BaseFragment
import com.firestore.ksgeyik.databinding.FragmentPostShareBinding
import com.firestore.ksgeyik.enums.ToolBarState
import com.firestore.ksgeyik.presentation.main.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.support.v4.toast

class PostShareFragment : BaseFragment<FragmentPostShareBinding, PostShareViewModel>() {

    init {
        (activity as MainActivity).setToolbar(ToolBarState.POSTSHARE)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity)?.activityPostShareBtn?.setOnClickListener {
            mViewModel.sharePost(mViewDataBinding?.activityPostShareEt?.text.toString())
        }
        mViewModel.liveData.observe(this, Observer {
            when (it) {
                true -> fragmentManager?.popBackStack()
                false -> toast("Fail")
            }
        })
    }

    override fun getViewModelBindingVariable(): Int {
        return BR.viewmodel
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_post_share
    }

}