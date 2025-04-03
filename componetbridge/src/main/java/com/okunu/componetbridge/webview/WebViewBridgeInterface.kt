package com.okunu.componetbridge.webview

import android.content.Context
import com.okunu.componetbridge.BridgeInterface

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
interface WebViewBridgeInterface : BridgeInterface {

    fun toWebViewActivity(context: Context, url: String = "", requestUrl: String = "")

}