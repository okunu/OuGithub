package com.okunu.github.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.okunu.componetbridge.provider.BridgeProviders
import com.okunu.componetbridge.search.SearchBridgeInterface
import com.okunu.componetbridge.user.UserBridgeInterface


/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
class MainViewPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm!!) {

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> BridgeProviders.instance.getBridge(SearchBridgeInterface::class.java).getSearchFragment()
        else -> BridgeProviders.instance.getBridge(UserBridgeInterface::class.java).getUserFragment()
    }

    override fun getCount(): Int = 2
}