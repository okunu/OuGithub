package com.okunu.login

import com.okunu.basic.repository.BaseRepository
import com.okunu.common.Constants
import com.okunu.common.extensions.RequestCallback
import com.okunu.common.extensions.request
import com.okunu.common.model.UserModel
import com.okunu.common.utils.CommonUtils
import com.okunu.network.GithubService
import com.okunu.network.HttpClient
import kotlinx.coroutines.CoroutineScope
import okhttp3.ResponseBody
import retrofit2.Response


/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
class LoginRepository(
    private val service: GithubService,
    scope: CoroutineScope
) : BaseRepository(scope) {

    fun getUser(callback: RequestCallback<UserModel>) {
        request(scope, callback) {
            service.getUser()
        }
    }

    fun getAccessToken(code: String, callback: RequestCallback<Response<ResponseBody>>) {
        request(scope, callback) {
            HttpClient.getServiceFromBaseUrl(HttpClient.GITHUB_BASE_URL).getAccessToken(
                CommonUtils.parseToJsonObject(
                    TokenRequestModel(
                        Constants.CLIENT_ID,
                        Constants.CLIENT_SECRET,
                        code,
                        Constants.REDIRECT_URI
                    )
                )
            )
        }
    }
}
