package com.okunu.login

import android.content.Intent
import android.net.Uri
import android.text.TextUtils
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.okunu.basic.activity.BaseActivity
import com.okunu.common.ARouterPaths
import com.okunu.common.Constants
import com.okunu.common.LoginToAuthorize
import com.okunu.common.LoginToMain
import com.okunu.common.PageDefault
import com.okunu.common.utils.ALog
import com.okunu.componetbridge.app.AppBridgeInterface
import com.okunu.componetbridge.provider.BridgeProviders
import com.okunu.componetbridge.user.UserBridgeInterface
import com.okunu.login.databinding.LoginActivityLoginBinding


/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
@Route(path = ARouterPaths.PATH_LOGIN_LOGIN)
class LoginActivity : BaseActivity<LoginActivityLoginBinding, LoginVM>() {

    override fun getVariableId(): Int = BR.vm

    private var mAuthorizationCode: String? = null

    override fun getLayoutId(): Int = R.layout.login_activity_login

    override fun getViewModelInstance(): LoginVM =
        LoginVM(BridgeProviders.instance.getBridge(AppBridgeInterface::class.java))

    override fun getViewModelClass(): Class<LoginVM> = LoginVM::class.java

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        getExtra(intent)
    }

    private fun getExtra(intent: Intent?) {
        // from author login
        mAuthorizationCode = intent?.data?.getQueryParameter(Constants.AUTHORIZATION_CODE)
        ALog.i("getExtra: $mAuthorizationCode")
        if (!TextUtils.isEmpty(mAuthorizationCode)) {
            viewModel.getAccessTokenFromCode(mAuthorizationCode ?: "")
        }
    }

    override fun addObserver() {
        super.addObserver()
        viewModel.hideSoftInput.observe(this, Observer {
            if (it) hideSoftInput()
        })
        viewModel.toPage.observe(this, Observer {
            when (it) {
                is LoginToMain -> {
                    ARouter.getInstance().build(ARouterPaths.PATH_APP_MAIN).navigation(this)
                    BridgeProviders.instance.getBridge(UserBridgeInterface::class.java).refreshUserInfo()
                    finish()
                }
                is LoginToAuthorize -> authorize()
                PageDefault -> Any()
                else -> {}
            }
        })
    }

    private fun authorize() {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(Constants.AUTHORIZATION_URL)))
    }
}
