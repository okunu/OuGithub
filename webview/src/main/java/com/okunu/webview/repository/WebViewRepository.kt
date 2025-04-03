package com.okunu.webview.repository

import com.okunu.basic.repository.BaseRepository
import com.okunu.common.extensions.RequestCallback
import com.okunu.common.extensions.request
import com.okunu.common.model.NotificationRequestUrlModel
import com.okunu.network.GithubService
import com.okunu.network.HttpClient
import kotlinx.coroutines.CoroutineScope

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
class WebViewRepository(
    private val service: GithubService,
    scope: CoroutineScope
) : BaseRepository(scope) {

    fun getNotificationRequestUrl(
        path: String,
        callback: RequestCallback<NotificationRequestUrlModel>
    ) {
        val finalPath = if (path.startsWith(HttpClient.API_GITHUB_BASE_URL)) {
            path.substring(HttpClient.API_GITHUB_BASE_URL.length + 1)
        } else {
            path
        }
        request(scope, callback) {
            service.getNotificationRequestUrl(finalPath)
        }
    }
}