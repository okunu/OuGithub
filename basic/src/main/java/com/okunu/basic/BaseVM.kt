package com.okunu.basic

import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import com.okunu.common.live.SingleLiveEvent
import com.okunu.common.utils.CommonUtils

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
abstract class BaseVM : AndroidViewModel(CommonUtils.getApp()) {

    val showLoading = SingleLiveEvent<Boolean>()

    abstract fun attach(savedInstanceState: Bundle? = null)

}
