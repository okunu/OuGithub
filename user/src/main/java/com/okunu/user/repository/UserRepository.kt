package com.okunu.user.repository

import com.okunu.basic.repository.BaseRepository
import com.okunu.common.extensions.RequestCallback
import com.okunu.common.extensions.request
import com.okunu.common.model.UserModel
import com.okunu.network.GithubService
import kotlinx.coroutines.CoroutineScope

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
class UserRepository(
    private val service: GithubService,
    scope: CoroutineScope
) : BaseRepository(scope) {

    fun getUser(callback: RequestCallback<UserModel>) {
        request(scope, callback) {
            service.getUser()
        }
    }
}