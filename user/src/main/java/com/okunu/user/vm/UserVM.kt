package com.okunu.user.vm

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope


import com.okunu.user.adapter.UserRecyclerViewAdapter
import com.okunu.user.repository.UserRepository
import com.okunu.basic.BaseVM
import com.okunu.common.extensions.RequestCallback
import com.okunu.common.live.SingleLiveEvent
import com.okunu.common.model.ResponseError
import com.okunu.common.model.ResponseSuccess
import com.okunu.common.model.TYPE_INFO
import com.okunu.common.model.UserModel
import com.okunu.common.utils.ALog
import com.okunu.componetbridge.app.AppBridgeInterface
import com.okunu.componetbridge.provider.BridgeProviders
import com.okunu.network.HttpClient
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
class UserVM(val userInfoVM: UserInfoVM) : BaseVM() {

    private val repository = UserRepository(HttpClient.getService(), viewModelScope)
    val userData = MutableLiveData<UserModel>()
    private val mAdapter = UserRecyclerViewAdapter(userInfoVM)
    val isRefreshing = SingleLiveEvent<Boolean>()
    val isRunning = AtomicBoolean(false)

    override fun attach(savedInstanceState: Bundle?) {
        ALog.i("UserVM attach")
        getUser(false)
    }

    fun getUser(refresh: Boolean) {
        if (isRunning.compareAndSet(false, true)) {
            if (!refresh) showLoading.value = true
            repository.getUser(object : RequestCallback<UserModel> {
                override fun onSuccess(result: ResponseSuccess<UserModel>) {
                    showLoading.value = false
                    isRefreshing.value = false
                    val userInfo = result.data
                    ALog.i("uservm getuser success: $userInfo")
                    userInfo?.itemType = TYPE_INFO
                    userInfo?.let {
                        if (refresh) {
                            mAdapter.clear()
                            mAdapter.notifyDataSetChanged()
                        }
                        mAdapter.addData(it)
                    }
                    isRunning.set(false)
                }

                override fun onError(error: ResponseError) {
                    showLoading.value = false
                    isRefreshing.value = false
                    mAdapter.clear()
                    mAdapter.notifyDataSetChanged()
                    ALog.d("uservm getuser error $error")
                    isRunning.set(false)
                }
            })
        }
    }

    fun logout() {
        val appBridge = BridgeProviders.instance.getBridge(AppBridgeInterface::class.java)
        appBridge.setAccessToken("")
        onRefreshListener()
    }

    fun createAdapter() = mAdapter

    fun onRefreshListener() {
        isRefreshing.value = true
        getUser(true)
    }
}