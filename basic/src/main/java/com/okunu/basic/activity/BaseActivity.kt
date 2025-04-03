package com.okunu.basic.activity

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.okunu.basic.BaseVM
import com.okunu.common.utils.LoadingUtils

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
abstract class BaseActivity<V : ViewDataBinding, M : BaseVM> : AppCompatActivity() {

    protected lateinit var viewDataBinding: V

    protected lateinit var viewModel: M


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return getViewModelInstance() as T
            }
        }).get(getViewModelClass())
        viewDataBinding.setVariable(getVariableId(), viewModel)
        viewModel.attach(savedInstanceState)
        viewDataBinding.lifecycleOwner = this
        addObserver()
    }

    open fun addObserver() {
        viewModel.showLoading.observe(this, Observer {
            LoadingUtils.loading(it, viewDataBinding.root as? ViewGroup)
        })
    }

    abstract fun getVariableId(): Int

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewModelInstance(): M

    abstract fun getViewModelClass(): Class<M>

    protected fun hideSoftInput() {
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
            window.decorView.windowToken,
            InputMethodManager.RESULT_UNCHANGED_SHOWN
        )
    }

    protected fun showSoftInput(view: View) {
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_FORCED)
    }
}
