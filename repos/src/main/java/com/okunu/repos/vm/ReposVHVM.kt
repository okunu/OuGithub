package com.okunu.repos.vm

import android.content.Context
import com.okunu.basic.BaseRecyclerVM
import com.okunu.common.model.ReposModel
import com.okunu.componetbridge.provider.BridgeProviders
import com.okunu.componetbridge.webview.WebViewBridgeInterface


/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
class ReposVHVM(private val context: Context) : BaseRecyclerVM<ReposModel>() {

    var data: ReposModel? = null

    override fun onBind(model: ReposModel?) {
        data = model
    }

    fun updateAtContent() = data?.run {
        val index = updated_at.indexOf("T")
        updated_at.substring(0, index)
    } ?: ""

    fun contentClick() {
        BridgeProviders.instance.getBridge(WebViewBridgeInterface::class.java)
            .toWebViewActivity(context, data?.html_url ?: "")
    }
}