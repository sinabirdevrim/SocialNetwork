package com.firestore.ksgeyik.presentation.main

import android.os.Bundle
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import com.firestore.ksgeyik.R
import com.firestore.ksgeyik.common.BaseActivity
import com.firestore.ksgeyik.common.Constants
import com.firestore.ksgeyik.databinding.ActivityMainBinding
import com.firestore.ksgeyik.enums.ToolBarState
import com.firestore.ksgeyik.extensions.replaceFragment
import com.firestore.ksgeyik.presentation.main.postlist.PostListFragment
import com.orhanobut.hawk.Hawk

class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openFragment(PostListFragment(), false)
        mViewModel.getUser()
        mViewModel.liveData.observe(this, Observer {
            Hawk.put(Constants.USER, it)
        })

        replaceFragment(PostListFragment.newInstance(), R.id.activityMainFl)
    }

    fun setToolbar(toolBarState: ToolBarState) {
        mViewModel.toolBarState.set(toolBarState)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModelBindingVariable(): Int {
        return BR.viewmodel
    }

}
