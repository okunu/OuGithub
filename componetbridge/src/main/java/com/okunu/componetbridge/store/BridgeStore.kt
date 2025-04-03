package com.okunu.componetbridge.store

import com.okunu.componetbridge.BridgeInterface

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
class BridgeStore {

    private val mMap = HashMap<String, BridgeInterface>()

    fun put(key: String, bridge: BridgeInterface) {
        mMap.put(key, bridge)?.onClear()
    }

    fun get(key: String): BridgeInterface? = mMap[key]

    fun clear() {
        for (item in mMap.values) {
            item.onClear()
        }
        mMap.clear()
    }
}