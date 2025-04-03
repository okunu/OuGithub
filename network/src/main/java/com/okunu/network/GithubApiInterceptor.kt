package com.okunu.network

import com.okunu.common.utils.ALog
import com.okunu.componetbridge.app.AppBridgeInterface
import com.okunu.componetbridge.provider.BridgeProviders
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
class GithubApiInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val appBridge =
            BridgeProviders.instance.getBridge(AppBridgeInterface::class.java)
        ALog.d("intercept url ${request.url} base auth: ${appBridge.getAuthorizationBasic()} token: ${appBridge.getAccessToken()}")

        val builder = request.newBuilder()
        val authorization = "token " + appBridge.getAccessToken()
        ALog.i("authorization: $authorization")
        if (!appBridge.getAccessToken().isNullOrBlank()) {
            builder.addHeader("Authorization", authorization)
        }
        val response = chain.proceed(builder.build())
        ALog.d("intercept url ${request.url}, response ${response.message} ,code ${response.code}")
        return response
    }
}
