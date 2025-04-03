package com.okunu.componetbridge.app

import com.okunu.componetbridge.BridgeInterface

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
interface AppBridgeInterface: BridgeInterface {

    /**
     * 获取用户的Authorization Basic
     */
    fun getAuthorizationBasic(): String?

    fun setAuthorizationBasic(authorization: String?)

    /**
     * 获取用户的AccessToken
     */
    fun getAccessToken(): String?

    fun setAccessToken(accessToken: String?)

    fun goSearchPage()
}