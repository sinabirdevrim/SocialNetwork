package com.firestore.ksgeyik.presentation.main

import android.os.Bundle
import androidx.databinding.library.baseAdapters.BR
import com.firestore.ksgeyik.R
import com.firestore.ksgeyik.common.BaseActivity
import com.firestore.ksgeyik.databinding.ActivityMainBinding
import com.firestore.ksgeyik.presentation.main.postlist.PostListFragment

class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openFragment(PostListFragment(), false)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModelBindingVariable(): Int {
        return BR.viewmodel
    }

}
