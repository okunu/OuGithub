package com.okunu.repos.vm

import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.okunu.basic.BaseVM
import com.okunu.common.extensions.RequestCallback
import com.okunu.common.live.SingleLiveEvent
import com.okunu.common.model.ReposModel
import com.okunu.common.model.ResponseError
import com.okunu.common.model.ResponseSuccess
import com.okunu.network.HttpClient

import com.okunu.repos.adapter.ReposAdapter
import com.okunu.repos.adapter.ReposIssueAdapter
import com.okunu.repos.repository.ReposRepository

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
class ReposVM : BaseVM() {

    private val repository = ReposRepository(HttpClient.getService(), viewModelScope)
    val adapter = ReposIssueAdapter()
    val isRefreshing = SingleLiveEvent<Boolean>()

    override fun attach(savedInstanceState: Bundle?) {
        getRepos(false)
    }

    private fun getRepos(refresh: Boolean) {
        if (!refresh) showLoading.value = true
        repository.getRepos(object : RequestCallback<List<ReposModel>> {
            override fun onSuccess(result: ResponseSuccess<List<ReposModel>>) {
                isRefreshing.value = false
                showLoading.value = false
                result.data?.let {
                    if (refresh) {
                        adapter.clear()
                        adapter.notifyDataSetChanged()
                    }
                    adapter.addData(it)
                }
            }

            override fun onError(error: ResponseError) {
                showLoading.value = false
                isRefreshing.value = false
            }

        })
    }

    fun onRefreshListener() {
        isRefreshing.value = true
        getRepos(true)
    }
}