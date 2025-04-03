package com.okunu.repos.repository

import com.okunu.basic.repository.BaseRepository
import com.okunu.common.extensions.RequestCallback
import com.okunu.common.extensions.request
import com.okunu.common.model.IssuesModel
import com.okunu.common.model.IssuesRequestModel
import com.okunu.common.model.ReposModel
import com.okunu.common.utils.ALog
import com.okunu.common.utils.CommonUtils
import com.okunu.network.GithubService
import kotlinx.coroutines.CoroutineScope

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
class ReposRepository(
    private val service: GithubService,
    scope: CoroutineScope
) : BaseRepository(scope) {

    fun getRepos(callback: RequestCallback<List<ReposModel>>) {
        request(scope, callback) {
            service.getPros(mapOf("visibility" to "all", "sort" to "pushed"))
        }
    }

    fun createIssues(repo: String, content: String, callback: RequestCallback<IssuesModel>) {
        request(scope, callback) {
            val json = CommonUtils.parseToJsonObject(IssuesRequestModel("i am a title", "test"))
            val username = CommonUtils.getRealUserName()
            ALog.i("real user name: ${CommonUtils.getRealUserName()}  $repo  $json")
            service.createIssues(username, repo, IssuesRequestModel("i am a title", content))
        }
    }
}