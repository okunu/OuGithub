package com.okunu.componetbridge.user

import androidx.fragment.app.Fragment

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
class DefaultUserBridge : UserBridgeInterface {

    override fun getUserFragment(): Fragment = Fragment()
    override fun refreshUserInfo() {

    }
}