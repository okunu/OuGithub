package com.okunu.componetbridge.user

import androidx.fragment.app.Fragment
import com.okunu.componetbridge.BridgeInterface

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
interface UserBridgeInterface: BridgeInterface {

    fun getUserFragment(): Fragment
    fun refreshUserInfo()
}