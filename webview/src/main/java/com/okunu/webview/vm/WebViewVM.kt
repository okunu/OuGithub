package com.okunu.webview.vm

import android.os.Bundle
import android.text.TextUtils
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.okunu.basic.BaseVM
import com.okunu.common.extensions.RequestCallback
import com.okunu.common.live.SingleLiveEvent
import com.okunu.common.model.NotificationRequestUrlModel
import com.okunu.common.model.ResponseError
import com.okunu.common.model.ResponseSuccess
import com.okunu.common.navigation.OnNavigationListener
import com.okunu.network.HttpClient

import com.okunu.webview.repository.WebViewRepository

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
class WebViewVM : BaseVM() {

    private val repository = WebViewRepository(HttpClient.getService(), viewModelScope)
    val url = MutableLiveData<String>()
    val backClick = SingleLiveEvent<Boolean>()

    override fun attach(savedInstanceState: Bundle?) {
        showLoading.value = true
    }

    fun request(requestUrl: String) {
        if (!TextUtils.isEmpty(requestUrl)) {
            repository.getNotificationRequestUrl(
                requestUrl,
                object : RequestCallback<NotificationRequestUrlModel> {
                    override fun onSuccess(result: ResponseSuccess<NotificationRequestUrlModel>) {
                        url.value = result.data?.html_url
                    }

                    override fun onError(error: ResponseError) {
                        showLoading.value = false
                    }

                })
        }
    }

    fun setWebViewClient() = object : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            showLoading.value = false
        }
    }

    fun setWebChromeClient() = object : WebChromeClient() {
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            if (newProgress >= 100) showLoading.value = false
        }
    }

    fun setOnNavigationListener() = object : OnNavigationListener {

        override fun onBackClick() {
            backClick.value = true
        }

        override fun onRightIconClick() {}
    }
}