package com.firestore.ksgeyik.presentation.main.search

import androidx.databinding.library.baseAdapters.BR
import com.firestore.ksgeyik.R
import com.firestore.ksgeyik.common.BaseFragment
import com.firestore.ksgeyik.databinding.FragmentSettingsBinding
import com.firestore.ksgeyik.enums.ToolBarState
import com.firestore.ksgeyik.presentation.main.MainActivity

class SettingsFragment : BaseFragment<FragmentSettingsBinding, SettingsViewModel>() {

    companion object {
        @JvmStatic
        fun newInstance() = SettingsFragment()
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