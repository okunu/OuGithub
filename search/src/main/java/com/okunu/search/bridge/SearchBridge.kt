package com.okunu.search.bridge

import androidx.fragment.app.Fragment
import com.okunu.search.fragment.SearchFragment
import com.okunu.componetbridge.search.SearchBridgeInterface

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
class SearchBridge : SearchBridgeInterface {

    override fun getSearchFragment(): Fragment = SearchFragment.getInstance()

}