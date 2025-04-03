package com.okunu.basic

import androidx.lifecycle.AndroidViewModel
import com.okunu.common.model.BaseRecyclerViewModel
import com.okunu.common.utils.CommonUtils

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
abstract class BaseRecyclerVM<in T : BaseRecyclerViewModel> :
    AndroidViewModel(CommonUtils.getApp()) {

    abstract fun onBind(model: T?)
}