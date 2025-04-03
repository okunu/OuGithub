package com.okunu.common.navigation

import android.app.Activity
import android.content.Context
import com.okunu.common.navigation.OnNavigationListener

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
class OnNavigationListenerImpl(private val context: Context) : OnNavigationListener {

    override fun onBackClick() {
        (context as? Activity)?.onBackPressed()
    }

    override fun onRightIconClick() {

    }
}
