package com.firestore.ksgeyik.common

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.firestore.ksgeyik.R
import com.firestore.ksgeyik.util.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import org.jetbrains.anko.AnkoLogger
import java.lang.reflect.ParameterizedType
import javax.inject.Inject


abstract class BaseActivity<B : ViewDataBinding, V : BaseViewModel> : DaggerAppCompatActivity(), AnkoLogger {

    val NO_VIEW_MODEL_BINDING_VARIABLE = -1

    lateinit var mViewModel: V
    var mViewDataBinding: B? = null

    @Inject
    lateinit var mViewModelFactory: ViewModelProviderFactory

    abstract fun getViewModelBindingVariable(): Int

    @LayoutRes
    abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        provideViewModel()
        performDataBinding()
    }

    private fun performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        if (getViewModelBindingVariable() != NO_VIEW_MODEL_BINDING_VARIABLE) {
            setViewModelBindingVariable()
        }
    }

    private fun setViewModelBindingVariable() {
        mViewDataBinding?.setVariable(getViewModelBindingVariable(), mViewModel)
        mViewDataBinding?.executePendingBindings()
    }

    private fun provideViewModel() {
        val clazz: Class<V> = getViewModelClass(javaClass)
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(clazz)
    }

    private fun getViewModelClass(aClass: Class<*>): Class<V> {
        val type = aClass.genericSuperclass

        return if (type is ParameterizedType) {
            type.actualTypeArguments[1] as Class<V>
        } else {
            getViewModelClass(aClass.superclass)
        }
    }

    fun openFragment(frNew: Fragment, isAddedBackStack: Boolean) {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        val frStack = manager.findFragmentByTag(frNew.tag)
        frStack?.let { transaction.replace(R.id.activityMainFl, frStack) } ?: transaction.replace(R.id.activityMainFl, frNew, frNew.tag)
        if (isAddedBackStack) transaction.addToBackStack(null)
        transaction.commit()
    }

}