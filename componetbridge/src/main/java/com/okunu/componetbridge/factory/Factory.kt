package com.okunu.componetbridge.factory

import com.okunu.componetbridge.BridgeInterface

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
interface Factory {

    fun <T : BridgeInterface> create(bridgeClazz: Class<T>): T
}