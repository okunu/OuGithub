package com.okunu.github.bridge

import com.okunu.common.utils.SPUtils
import com.okunu.componetbridge.app.AppBridgeInterface

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
class AppBridge : AppBridgeInterface {

    override fun getAuthorizationBasic(): String? = SPUtils.getString(SPUtils.KEY_AUTHORIZATION_BASIC)

    override fun setAuthorizationBasic(authorization: String?) {
        SPUtils.putString(
            SPUtils.KEY_AUTHORIZATION_BASIC,
            authorization
        )
    }

    override fun getAccessToken(): String? = SPUtils.getString(SPUtils.KEY_ACCESS_TOKEN)

    override fun setAccessToken(accessToken: String?) {
        SPUtils.putString(
            SPUtils.KEY_ACCESS_TOKEN,
            accessToken
        )
    }

    override fun goSearchPage() {

    }

}