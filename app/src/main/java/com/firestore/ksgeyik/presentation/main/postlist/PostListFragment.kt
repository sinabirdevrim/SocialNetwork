package com.firestore.ksgeyik.presentation.main.postlist

import android.os.Bundle
import android.view.View
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import com.firestore.ksgeyik.R
import com.firestore.ksgeyik.common.BaseFragment
import com.firestore.ksgeyik.databinding.FragmentPostListBinding
import com.firestore.ksgeyik.enums.ToolBarState
import com.firestore.ksgeyik.extensions.replaceFragment
import com.firestore.ksgeyik.presentation.main.MainActivity
import com.firestore.ksgeyik.presentation.main.postshare.PostShareFragment

class PostListFragment : BaseFragment<FragmentPostListBinding, PostListViewModel>() {

    companion object {
        @JvmStatic
        fun newInstance() = PostListFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //lifecycle.addObserver(mViewModel)
        mViewModel.getPostList()
        mViewModel.liveData.observe(this, Observer {
            mViewDataBinding?.fragmentPostListRv?.adapter =
                PostListAdapter(it, getBaseActivity()?.baseContext,
                    {
                        mViewModel.likeOrUnlikePost(it)
                    },
                    {
                        mViewModel.commentPost(it)
                    }
                )
        })
        mViewDataBinding?.fragmentPostListProfileFab?.setOnClickListener {
            (activity as MainActivity).replaceFragment(
                PostShareFragment.newInstance(),
                R.id.activityMainFl
            )
        }
    }

    override fun getViewModelBindingVariable(): Int {
        return BR.viewmodel
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_post_list
    }

    override fun setToolbarState() {
        (activity as MainActivity).setToolbar(ToolBarState.MAIN)
    }
}