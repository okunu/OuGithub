package com.okunu.github

import android.app.Application
import android.util.Log
import com.alibaba.android.arouter.BuildConfig
import com.alibaba.android.arouter.launcher.ARouter
import com.okunu.common.utils.ALog
import com.okunu.common.utils.CommonUtils
import com.okunu.common.utils.SPUtils
import com.okunu.componetbridge.BridgeInterface
import com.okunu.componetbridge.factory.Factory
import com.okunu.componetbridge.provider.BridgeProviders
import com.okunu.github.bridge.AppBridge
import com.okunu.repos.bridge.ReposBridge
import com.okunu.search.bridge.SearchBridge
import com.okunu.user.bridge.UserBridge
import com.okunu.webview.bridge.WebViewBridge

class App : Application(){

    companion object {
        private lateinit var instance: App

        fun getInstance(): App {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        SPUtils.init(this)
        CommonUtils.initApp(this)
        initRouter(this)
        registerBridge()
    }

    private fun registerBridge() {
        BridgeProviders.instance.register(AppBridge::class.java, object : Factory {
            override fun <T : BridgeInterface> create(bridgeClazz: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return AppBridge() as T
            }
        })
            .register(SearchBridge::class.java)
            .register(ReposBridge::class.java)
            .register(UserBridge::class.java)
            .register(WebViewBridge::class.java)
    }

    private fun initRouter(app: Application) {
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(app)
        ALog.d("initRouter")
    }

}