package com.okunu.search.repository


import com.okunu.basic.repository.BaseRepository
import com.okunu.common.extensions.RequestCallback
import com.okunu.common.extensions.request
import com.okunu.common.model.SearchModel
import com.okunu.network.GithubService
import kotlinx.coroutines.CoroutineScope

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
class SearchRepository(
    private val service: GithubService,
    scope: CoroutineScope
) : BaseRepository(scope) {

    fun searchRepository(query: String, startSort: String, callback: RequestCallback<SearchModel>) {
        request(
            scope = scope,
            callback = callback
        ) { service.searchRepository(hashMapOf(
            "q" to query,
            "sort" to "stars",
            "order" to startSort
        )) }
    }
}