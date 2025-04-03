package com.okunu.componetbridge.search

import androidx.fragment.app.Fragment
import com.okunu.componetbridge.BridgeInterface

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
interface SearchBridgeInterface : BridgeInterface {

    fun getSearchFragment(): Fragment
}