package com.okunu.login

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Base64
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope

import com.okunu.basic.BaseVM
import com.okunu.common.LoginToAuthorize
import com.okunu.common.LoginToMain
import com.okunu.common.PageDefault
import com.okunu.common.ToPageStatus
import com.okunu.common.extensions.RequestCallback
import com.okunu.common.extensions.createTextWatcher
import com.okunu.common.model.ResponseError
import com.okunu.common.model.ResponseSuccess
import com.okunu.common.model.UserModel
import com.okunu.common.utils.ALog
import com.okunu.common.utils.CommonUtils
import com.okunu.common.utils.SPUtils
import com.okunu.componetbridge.app.AppBridgeInterface
import com.okunu.network.HttpClient
import kotlinx.coroutines.Job
import okhttp3.ResponseBody
import retrofit2.Response
import java.io.IOException

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
class LoginVM(private val appBridge: AppBridgeInterface) : BaseVM() {

    private val repository = LoginRepository(HttpClient.getService(), viewModelScope)
    val username = MutableLiveData<String>(CommonUtils.getUsername())
    val password = MutableLiveData<String>(CommonUtils.getPassword())
    val signInEnable = MutableLiveData<Boolean>(false)
    val hideSoftInput = MutableLiveData(false)
    val toPage = MutableLiveData<ToPageStatus>(PageDefault)
    private var mTokenJob: Job? = null

    override fun attach(savedInstanceState: Bundle?) {
    }

    override fun onCleared() {
        super.onCleared()
        mTokenJob?.cancel()
    }

    fun usernameTextChangedListener(): TextWatcher =
        createTextWatcher({
            username.value = it.toString()
            signInEnable.value = !TextUtils.isEmpty(it) && !TextUtils.isEmpty(password.value)
        })

    fun passwordTextChangedListener(): TextWatcher =
        createTextWatcher({
            password.value = it.toString()
            signInEnable.value = !TextUtils.isEmpty(it) && !TextUtils.isEmpty(username.value)
        })

    fun addActionListener(): TextView.OnEditorActionListener =
        TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                hideSoftInput.value = true
                loginFromUsername()
                true
            } else {
                false
            }
        }

    fun signInClick() {
        showLoading.value = true
        loginFromUsername()
    }

    fun authorizeClick() {
        toPage.value = LoginToAuthorize
    }

    private fun loginFromUsername() {
        SPUtils.putString(SPUtils.KEY_USER_NAME, username.value ?: "")
        SPUtils.putString(SPUtils.KEY_PASSWORD, password.value ?: "")
        ALog.i("username: ${username.value}  ${password.value}")
        appBridge.setAuthorizationBasic(
            Base64.encodeToString(
                (username.value + ":" + password.value).toByteArray(), Base64.NO_WRAP
            )
        )
        getUser()
    }

    private fun getUser() {
        repository.getUser(object : RequestCallback<UserModel> {
            override fun onSuccess(result: ResponseSuccess<UserModel>) {
                val user = result.data
                ALog.i("user ori data: ${result.data}")
                ALog.i("getUser ${user?.login}")
                // login success, save user info
                SPUtils.putString(SPUtils.KEY_REAL_USER_NAME, user?.login)
                SPUtils.putString(
                    SPUtils.KEY_AUTHORIZATION_BASIC,
                    appBridge.getAuthorizationBasic()
                )
                SPUtils.putString(SPUtils.KEY_ACCESS_TOKEN, appBridge.getAccessToken())
                showLoading.postValue(false)
                toPage.postValue(LoginToMain)
            }

            override fun onError(error: ResponseError) {
                showLoading.value = false
                ALog.i("getuser failed $error")
            }

        })
    }

    fun getAccessTokenFromCode(code: String) {
        showLoading.value = true
        repository.getAccessToken(code, object : RequestCallback<Response<ResponseBody>> {
            override fun onSuccess(result: ResponseSuccess<Response<ResponseBody>>) {
                try {
                    appBridge.setAccessToken(
                        result.data?.body()?.string()?.split("=")?.get(1)?.split("&")?.get(
                            0
                        )
                    )
                    getUser()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

            override fun onError(error: ResponseError) {
                showLoading.value = false
            }
        })
    }
}
