package com.firestore.ksgeyik.presentation.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.library.baseAdapters.BR
import com.firestore.android.repository.model.User
import com.firestore.ksgeyik.common.BaseActivity
import com.firestore.ksgeyik.common.Constants
import com.firestore.ksgeyik.databinding.ActivityMainBinding
import com.firestore.ksgeyik.enums.ToolBarState
import com.firestore.ksgeyik.extensions.replaceFragmentFirst
import com.firestore.ksgeyik.presentation.main.postlist.PostListFragment
import com.orhanobut.hawk.Hawk


class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        replaceFragmentFirst(
            PostListFragment.newInstance(),
            com.firestore.ksgeyik.R.id.activityMainFl
        )
        val user = Hawk.get<User>(Constants.USER)
        mViewModel.setToolbarImage(user.photoUrl)
    }

    fun setToolbar(toolBarState: ToolBarState) {
        mViewModel.toolBarState.set(toolBarState)
    }

    fun getShareButton(): View? {
        return mViewDataBinding?.activityMainShareTv
    }

    override fun getLayoutId(): Int {
        return com.firestore.ksgeyik.R.layout.activity_main
    }

    override fun getViewModelBindingVariable(): Int {
        return BR.viewmodel
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val fragment = supportFragmentManager.findFragmentByTag("PostShareFragment")
        fragment?.onActivityResult(requestCode, resultCode, data)
    }

}
