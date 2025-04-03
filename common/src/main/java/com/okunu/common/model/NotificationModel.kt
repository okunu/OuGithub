package com.okunu.common.model

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */

data class NotificationModel(
    val id: String,
    val repository: ReposModel,
    val subject: SubjectModel,
    val reason: String,
    var unread: Boolean,
    val updated_at: String,
    val last_read_at: String,
    val url: String
) : BaseRecyclerViewModel()

data class SubjectModel(
    val title: String,
    val url: String,
    val latest_comment_url: String,
    val type: String
)

data class NotificationRequestUrlModel(
    val url: String,
    val id: Long,
    val html_url: String,
    val userModel: UserModel,
    val body: String
)
