package com.okunu.repos.vm

import android.os.Bundle
import android.text.TextUtils
import android.text.TextWatcher
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.okunu.basic.BaseVM
import com.okunu.common.extensions.RequestCallback
import com.okunu.common.extensions.createTextWatcher
import com.okunu.common.live.SingleLiveEvent
import com.okunu.common.model.IssuesModel
import com.okunu.common.model.ReposModel
import com.okunu.common.model.ResponseError
import com.okunu.common.model.ResponseSuccess
import com.okunu.common.utils.ALog
import com.okunu.common.utils.CommonUtils
import com.okunu.network.HttpClient

import com.okunu.repos.adapter.ReposAdapter
import com.okunu.repos.adapter.ReposIssueAdapter
import com.okunu.repos.repository.ReposRepository

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
class CreateIssueVM : BaseVM() {

    private val repository = ReposRepository(HttpClient.getService(), viewModelScope)

    val repoName = MutableLiveData<String>("")
    val issueContent = MutableLiveData<String>("")
    override fun attach(savedInstanceState: Bundle?) {

    }

    fun issueTextChangedListener(): TextWatcher =
        createTextWatcher({
            issueContent.value = it.toString()
        })

    fun submitIssue() {
        val txt = issueContent.value
        val name = repoName.value
        if (txt.isNullOrBlank() || name.isNullOrBlank()) {
            ALog.i("no input issue")
            return
        }
        repository.createIssues(name, txt, object : RequestCallback<IssuesModel>{
            override fun onSuccess(result: ResponseSuccess<IssuesModel>) {
                ALog.i("createIssues onSuccess ${result.data}")
            }

            override fun onError(error: ResponseError) {
                ALog.i("createIssues onError ${error.t?.message}")
            }

        })
    }


}