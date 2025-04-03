package com.okunu.webview.bridge

import android.content.Context
import androidx.core.os.bundleOf
import com.alibaba.android.arouter.launcher.ARouter
import com.okunu.common.ARouterPaths
import com.okunu.componetbridge.webview.WebViewBridgeInterface


/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
class WebViewBridge : WebViewBridgeInterface {

    override fun toWebViewActivity(context: Context, url: String, requestUrl: String) {
        ARouter.getInstance().build(ARouterPaths.PATH_WEBVIEW_WEBVIEW).with(
            bundleOf("url" to url, "requestUrl" to requestUrl)
        ).navigation(context)
    }

}