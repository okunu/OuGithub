package com.okunu.basic.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.okunu.basic.BaseVM
import com.okunu.common.utils.ALog
import com.okunu.common.utils.LoadingUtils

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
abstract class BaseFragment<V : ViewDataBinding, M : BaseVM> : Fragment() {

    protected lateinit var viewDataBinding: V
    protected lateinit var viewModel: M

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ALog.d("$this: onCreateView")
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        viewDataBinding.lifecycleOwner = this
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return getViewModelInstance() as T
            }
        }).get(getViewModelClass())
        viewDataBinding.setVariable(getVariableId(), viewModel)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ALog.d("$this: onViewCreated")
        addObserver()
        viewModel.attach(savedInstanceState)
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

}
