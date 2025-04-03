package com.okunu.basic.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import com.okunu.basic.BaseRecyclerVM
import com.okunu.common.model.BaseRecyclerViewModel

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
class CommonRecyclerViewVH<out VB : ViewDataBinding, out M : BaseRecyclerViewModel> :
    BaseRecyclerViewVH<VB, M> {

    constructor(
        view: ViewGroup, @LayoutRes layoutId: Int,
        vm: BaseRecyclerVM<M>?,
        variableId: Int,
        outerLifecycle: Lifecycle? = null
    ) : super(
        LayoutInflater.from(view.context).inflate(layoutId, view, false),
        vm,
        variableId,
        outerLifecycle
    )

    constructor(
        view: View,
        vm: BaseRecyclerVM<M>?,
        variableId: Int,
        outerLifecycle: Lifecycle? = null
    ) : super(view, vm, variableId, outerLifecycle)

}