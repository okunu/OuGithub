package com.okunu.common.model


/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
data class SearchModel(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<ReposModel>
)
