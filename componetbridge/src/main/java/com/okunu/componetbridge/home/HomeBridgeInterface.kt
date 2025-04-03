package com.okunu.componetbridge.home

import androidx.fragment.app.Fragment
import com.okunu.componetbridge.BridgeInterface

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
interface HomeBridgeInterface: BridgeInterface {

    fun getHomeFragment(): Fragment
}