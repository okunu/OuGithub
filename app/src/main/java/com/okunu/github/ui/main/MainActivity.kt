package com.okunu.github.ui.main

import android.view.KeyEvent
import android.widget.Toast
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.okunu.basic.activity.BaseActivity
import com.okunu.common.ARouterPaths
import com.okunu.common.utils.CommonUtils
import com.okunu.componetbridge.provider.BridgeProviders
import com.okunu.github.BR
import com.okunu.github.R
import com.okunu.github.databinding.ActivityMainBinding
import kotlin.system.exitProcess

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
@Route(path = ARouterPaths.PATH_APP_MAIN)
class MainActivity : BaseActivity<ActivityMainBinding, MainVM>() {

    companion object {
        private const val USER_POS = 1
    }

    override fun getVariableId(): Int = BR.vm

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun getViewModelInstance(): MainVM = MainVM(supportFragmentManager)

    override fun getViewModelClass(): Class<MainVM> = MainVM::class.java

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return if (viewModel.exitApp()) {
                super.onKeyUp(keyCode, event)
            } else {
                Toast.makeText(
                    this,
                    getString(R.string.press_again_to_exit_app),
                    Toast.LENGTH_SHORT
                ).show()
                true
            }
        }
        return super.onKeyUp(keyCode, event)
    }

    fun goSearchPage() {
        viewModel.goSearchPage()
    }

    override fun addObserver() {
        super.addObserver()
        viewModel.selectedPosition.observe(this, Observer {
            when (it) {
                USER_POS -> {
                    if (!CommonUtils.hasLogin()) {
                        ARouter.getInstance().build(ARouterPaths.PATH_LOGIN_LOGIN).navigation(CommonUtils.getApp())
                    }
                }
                else -> {
                    Any()
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        BridgeProviders.instance.clear()
        exitProcess(0)
    }

}