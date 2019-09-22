package com.firestore.ksgeyik.presentation.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.library.baseAdapters.BR
import com.firestore.android.repository.model.User
import com.firestore.ksgeyik.R
import com.firestore.ksgeyik.common.BaseActivity
import com.firestore.ksgeyik.common.Constants
import com.firestore.ksgeyik.databinding.ActivityMainBinding
import com.firestore.ksgeyik.enums.ToolBarState
import com.firestore.ksgeyik.extensions.replaceFragment
import com.firestore.ksgeyik.extensions.replaceFragmentFirst
import com.firestore.ksgeyik.presentation.main.postlist.PostListFragment
import com.firestore.ksgeyik.presentation.main.postshare.PostShareFragment
import com.firestore.ksgeyik.presentation.main.search.SearchFragment
import com.firestore.ksgeyik.presentation.main.search.SettingsFragment
import com.orhanobut.hawk.Hawk


class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        setToolbarImage()
        navigationChangeListener()
        navigationClickListener()
    }

    private fun init() {
        replaceFragmentFirst(
            PostListFragment.newInstance(),
            R.id.activityMainFl
        )
    }

    private fun setToolbarImage() {
        val user: User? = Hawk.get<User>(Constants.USER)
        mViewModel.setToolbarImage(user?.photoUrl)
    }

    private fun navigationClickListener() {
        mViewDataBinding?.navigation?.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigationHome -> {
                    replaceFragment(PostListFragment.newInstance(), R.id.activityMainFl)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigationSearch -> {
                    replaceFragment(SearchFragment.newInstance(), R.id.activityMainFl)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigationSettings -> {
                    replaceFragment(SettingsFragment.newInstance(), R.id.activityMainFl)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }

    /**
     *      En üstteki fragmentı bul ve ona göre bottomnavigationı değiştir
     */
    private fun navigationChangeListener() {
        supportFragmentManager.addOnBackStackChangedListener {
            when (supportFragmentManager.fragments.get(supportFragmentManager.fragments.size - 1)) {
                is PostShareFragment, is PostListFragment -> {
                    mViewDataBinding?.navigation?.menu?.getItem(0)?.isChecked = true
                }
                is SearchFragment -> {
                    mViewDataBinding?.navigation?.menu?.getItem(1)?.isChecked = true
                }
                is SettingsFragment -> {
                    mViewDataBinding?.navigation?.menu?.getItem(2)?.isChecked = true
                }
                else -> {
                    Log.d("", "")
                }
            }
        }
    }

    fun setToolbar(toolBarState: ToolBarState) {
        mViewModel.toolBarState.set(toolBarState)
    }

    fun getShareButton(): View? {
        return mViewDataBinding?.activityMainShareTv
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModelBindingVariable(): Int {
        return BR.viewmodel
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val fragment = supportFragmentManager.findFragmentByTag("PostShareFragment")
        fragment?.onActivityResult(requestCode, resultCode, data)
    }

}
