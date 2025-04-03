package com.okunu.basic.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView
import com.okunu.basic.BaseRecyclerVM
import com.okunu.basic.lifecycle.VHLifecycleObserver
import com.okunu.common.model.BaseRecyclerViewModel

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
abstract class BaseRecyclerViewVH<out VB : ViewDataBinding, out M : BaseRecyclerViewModel> :
    RecyclerView.ViewHolder, LifecycleOwner {

    private var mViewDataBinding: VB? = null
    private var mVM: BaseRecyclerVM<M>? = null
    private var mVariableId: Int = 0
    private lateinit var mLifecycle: LifecycleRegistry
    private var mOuterLifecycle: Lifecycle? = null

    constructor(
        parent: ViewGroup, @LayoutRes layoutId: Int,
        vm: BaseRecyclerVM<M>?,
        variableId: Int,
        outerLifecycle: Lifecycle? = null
    ) : super(
        LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
    ) {
        init(vm, variableId, outerLifecycle)
    }

    constructor(
        view: View,
        vm: BaseRecyclerVM<M>?,
        variableId: Int,
        outerLifecycle: Lifecycle? = null
    ) : super(view) {
        init(vm, variableId, outerLifecycle)
    }

    init {
        mViewDataBinding = DataBindingUtil.bind(itemView)
    }

    private fun init(vm: BaseRecyclerVM<M>?, variableId: Int, outerLifecycle: Lifecycle?) {
        mVM = vm
        mVariableId = variableId
        mOuterLifecycle = outerLifecycle
        register()
    }

    private fun register() {
        mLifecycle = LifecycleRegistry(this)
        mOuterLifecycle?.let {
            it.addObserver(VHLifecycleObserver(it, mLifecycle))
            mViewDataBinding?.lifecycleOwner = this
        }
    }

    open fun bind(data: BaseRecyclerViewModel?) {
        mViewDataBinding?.setVariable(mVariableId, mVM)
        @Suppress("UNCHECKED_CAST")
        mVM?.onBind(data as M?)
        mViewDataBinding?.executePendingBindings()
    }

//    fun getLifecycle(): Lifecycle = mLifecycle

    override val lifecycle: Lifecycle
        get() = mLifecycle
}
