package com.okunu.login

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
data class IssuesRequestModel(val title: String)

data class TokenRequestModel(
    val client_id: String,
    val client_secret: String,
    val code: String,
    val redirect_uri: String
)
