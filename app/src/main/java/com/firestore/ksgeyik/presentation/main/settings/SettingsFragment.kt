package com.firestore.ksgeyik.presentation.main.search

import android.os.Bundle
import android.view.View
import androidx.databinding.library.baseAdapters.BR
import com.firestore.ksgeyik.R
import com.firestore.ksgeyik.common.BaseFragment
import com.firestore.ksgeyik.common.Constants
import com.firestore.ksgeyik.databinding.FragmentSettingsBinding
import com.firestore.ksgeyik.enums.ToolBarState
import com.firestore.ksgeyik.presentation.SplashActivity
import com.firestore.ksgeyik.presentation.main.MainActivity
import com.orhanobut.hawk.Hawk
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import org.jetbrains.anko.support.v4.intentFor

class SettingsFragment : BaseFragment<FragmentSettingsBinding, SettingsViewModel>() {

    companion object {
        @JvmStatic
        fun newInstance() = SettingsFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setonClicks()
    }

    private fun setonClicks() {
        mViewDataBinding?.fragmentSettingsLogoutTv?.setOnClickListener {
            Hawk.put(Constants.IS_LOGIN, false)
            Hawk.put(Constants.USER_ID, null)
            Hawk.put(Constants.USER, null)
            startActivity(intentFor<SplashActivity>().clearTop().newTask())
            appCompatActivity?.finish()
        }
    }

    override fun getViewModelBindingVariable(): Int {
        return BR.viewmodel
    }

    override fun setToolbarState() {
        (activity as MainActivity).setToolbar(ToolBarState.MAIN)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_settings
    }

}