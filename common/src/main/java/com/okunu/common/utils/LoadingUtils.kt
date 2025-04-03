package com.okunu.common.utils

import android.view.ViewGroup
import com.okunu.common.custom.LoadingView

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
object LoadingUtils {

    fun loading(show: Boolean, target: ViewGroup?) {
        if (show) showLoading(target) else dismissLoading(target)
    }

    private fun showLoading(target: ViewGroup?) {
        target?.run {
            if (childCount <= 0 || getChildAt(childCount - 1) !is LoadingView) {
                addView(LoadingView(context))
            }
        }
    }

    private fun dismissLoading(target: ViewGroup?) {
        target?.run {
            if (childCount > 0 && getChildAt(childCount - 1) is LoadingView) {
                removeViewAt(childCount - 1)
            }
        }
    }
}