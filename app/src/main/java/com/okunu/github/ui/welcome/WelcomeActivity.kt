package com.okunu.github.ui.welcome

import android.view.KeyEvent
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.launcher.ARouter
import com.okunu.basic.activity.BaseActivity
import com.okunu.common.ARouterPaths
import com.okunu.common.PageDefault
import com.okunu.common.WelcomeToLogin
import com.okunu.common.WelcomeToMain
import com.okunu.github.BR
import com.okunu.github.R
import com.okunu.github.databinding.ActivityWelcomeBinding

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
class WelcomeActivity : BaseActivity<ActivityWelcomeBinding, WelcomeVM>() {

    override fun getVariableId(): Int = BR.vm

    override fun getLayoutId(): Int = R.layout.activity_welcome

    override fun getViewModelInstance(): WelcomeVM = WelcomeVM()

    override fun getViewModelClass(): Class<WelcomeVM> = WelcomeVM::class.java

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true
        }
        return super.onKeyUp(keyCode, event)
    }

    override fun addObserver() {
        super.addObserver()
        viewModel.toPage.observe(this, Observer {
            when (it) {
                is WelcomeToMain -> {
                    ARouter.getInstance().build(ARouterPaths.PATH_APP_MAIN).navigation(this)
                    finish()
                }
                is WelcomeToLogin -> {
                    ARouter.getInstance().build(ARouterPaths.PATH_LOGIN_LOGIN).navigation(this)
                    finish()
                }
                is PageDefault -> Any()
                else -> {
                        Any()
                }
            }
        })
    }

    companion object {
        private const val TAG = "WelcomeActivity"
    }
}