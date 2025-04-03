package com.okunu.componetbridge.factory

import com.okunu.componetbridge.BridgeInterface

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
class NewInstanceFactory : Factory {

    companion object {
        val instance: NewInstanceFactory by lazy { NewInstanceFactory() }
    }

    override fun <T : BridgeInterface> create(bridgeClazz: Class<T>): T = try {
        bridgeClazz.newInstance()
    } catch (e: InstantiationException) {
        throw RuntimeException("Cannot create an instance of $bridgeClazz", e)
    } catch (e: IllegalAccessException) {
        throw RuntimeException("Cannot create an instance of $bridgeClazz", e)
    }

}