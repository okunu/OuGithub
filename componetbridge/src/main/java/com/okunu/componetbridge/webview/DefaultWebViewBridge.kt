package com.okunu.componetbridge.webview

import android.content.Context
import android.widget.Toast

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
class DefaultWebViewBridge : WebViewBridgeInterface {

    override fun toWebViewActivity(context: Context, url: String, requestUrl: String) {
        Toast.makeText(context, "toWebViewActivity: $url", Toast.LENGTH_LONG).show()
    }
}