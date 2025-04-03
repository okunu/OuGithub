package com.okunu.github.ui.welcome

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.okunu.basic.BaseVM
import com.okunu.common.PageDefault
import com.okunu.common.ToPageStatus
import com.okunu.common.WelcomeToLogin
import com.okunu.common.WelcomeToMain
import com.okunu.common.utils.ALog
import com.okunu.common.utils.CommonUtils
import com.okunu.common.utils.SPUtils

import java.util.*

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
class WelcomeVM : BaseVM() {

    val toPage = MutableLiveData<ToPageStatus>(PageDefault)
    private var mTimer: Timer? = null

    override fun attach(savedInstanceState: Bundle?) {
        startDelay()
    }

    override fun onCleared() {
        super.onCleared()
        mTimer?.cancel()
        mTimer = null
    }

    private fun startDelay() {
        mTimer = Timer().apply {
            schedule(object : TimerTask() {
                override fun run() {
                    mTimer = null
                    ALog.i("haslogin: ${CommonUtils.hasLogin()}")
                    ALog.i("base auth: ${SPUtils.getString(SPUtils.KEY_AUTHORIZATION_BASIC)}")
                    ALog.i("access token: ${SPUtils.getString(SPUtils.KEY_ACCESS_TOKEN)}")
                    //toPage.postValue(if (CommonUtils.hasLogin()) WelcomeToMain else WelcomeToLogin)
                    toPage.postValue(WelcomeToMain)
                }

            }, 1500)
        }
    }
}