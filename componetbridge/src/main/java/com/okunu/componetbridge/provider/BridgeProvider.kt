package com.okunu.componetbridge.provider

import com.okunu.componetbridge.BridgeInterface
import com.okunu.componetbridge.factory.Factory
import com.okunu.componetbridge.store.BridgeStore

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
class BridgeProvider(private val factory: Factory) {

    val bridgeStore = BridgeStore()

    companion object {
        private const val DEFAULT_KEY = "com.okunu.componentbridge"
    }

    fun <T : BridgeInterface> get(key: String, bridgeClass: Class<T>): T {
        var componentBridge = bridgeStore.get(key)
        if (bridgeClass.isInstance(componentBridge)) {
            @Suppress("UNCHECKED_CAST")
            return componentBridge as T
        }
        componentBridge = factory.create(bridgeClass)
        bridgeStore.put(key, componentBridge)
        return componentBridge
    }

    fun <T : BridgeInterface> get(bridgeClass: Class<T>): T =
        get(DEFAULT_KEY + "@" + bridgeClass.canonicalName, bridgeClass)
}