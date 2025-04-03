package com.okunu.common.model

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
data class FollowersModel(
    val login: String,
    val avatar_url: String,
    val html_url: String
) : BaseRecyclerViewModel()