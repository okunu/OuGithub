package com.okunu.user.bridge

import androidx.fragment.app.Fragment
import com.okunu.componetbridge.user.UserBridgeInterface
import com.okunu.user.fragment.UserFragment

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
class UserBridge : UserBridgeInterface {

    override fun getUserFragment(): Fragment = UserFragment.getInstance()

    override fun refreshUserInfo() {
        UserFragment.getInstance().refreshData()
    }
}