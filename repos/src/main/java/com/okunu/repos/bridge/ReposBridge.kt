package com.okunu.repos.bridge

import android.content.Context
import androidx.core.os.bundleOf
import com.alibaba.android.arouter.launcher.ARouter
import com.okunu.repos.adapter.ReposAdapter
import com.okunu.basic.recyclerview.BaseRecyclerViewAdapter
import com.okunu.common.ARouterPaths
import com.okunu.componetbridge.repos.ReposBridgeInterface


/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
class ReposBridge : ReposBridgeInterface {

    override fun createReposAdapter(): BaseRecyclerViewAdapter = ReposAdapter()

    override fun toReposActivity(context: Context) {
        ARouter.getInstance().build(ARouterPaths.PATH_REPOS_REPOS).navigation(context)
    }

    fun toCreateIssueActivity(context: Context, repoName: String) {
        ARouter.getInstance().build(ARouterPaths.PATH_USER_SUBMIT_ISSUE).with(
            bundleOf("repoName" to repoName)
        ).navigation(context)
    }

}