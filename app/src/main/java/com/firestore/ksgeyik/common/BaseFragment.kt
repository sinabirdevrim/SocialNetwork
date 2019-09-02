package com.firestore.ksgeyik.common

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import com.firestore.ksgeyik.util.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import org.jetbrains.anko.AnkoLogger
import java.lang.reflect.ParameterizedType
import javax.inject.Inject


abstract class BaseFragment<B : ViewDataBinding, V : BaseViewModel> : DaggerFragment(), AnkoLogger {

    private var mActivity: BaseActivity<*, *>? = null
    lateinit var mRootView: View

    lateinit var mViewModel: V
    var mViewDataBinding: B? = null

    @Inject
    lateinit var mViewModelFactory: ViewModelProviderFactory

    abstract fun getViewModelBindingVariable(): Int

    protected val appCompatActivity: AppCompatActivity?
        get() = activity as AppCompatActivity?

    @LayoutRes
    abstract fun getLayoutId(): Int

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity<*, *>) {
            val activity = context as BaseActivity<*, *>?
            this.mActivity = activity
        }
    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = provideViewModel()
        setHasOptionsMenu(false)
    }

    override fun onCreateView(
        @NonNull inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        mRootView = mViewDataBinding!!.root
        return mRootView
    }

    override fun onDetach() {
        mActivity = null
        super.onDetach()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding?.setVariable(getViewModelBindingVariable(), mViewModel)
        mViewDataBinding?.lifecycleOwner = this
        mViewDataBinding?.executePendingBindings()
    }

    fun getBaseActivity(): BaseActivity<*, *>? {
        return mActivity
    }

    private fun provideViewModel(): V {
        val clazz: Class<V> = getViewModelClass(javaClass)
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(clazz)
        return mViewModel
    }

    private fun getViewModelClass(aClass: Class<*>): Class<V> {
        val type = aClass.genericSuperclass

        return if (type is ParameterizedType) {
            type.actualTypeArguments[1] as Class<V>
        } else {
            getViewModelClass(aClass.superclass)
        }
    }
}