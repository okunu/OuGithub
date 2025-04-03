package com.okunu.componetbridge.notification

import androidx.fragment.app.Fragment

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
class DefaultNotificationBridge : NotificationBridgeInterface {

    override fun getNotificationFragment(): Fragment = Fragment()

}