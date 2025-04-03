package com.okunu.componetbridge.repos

import android.content.Context
import android.widget.Toast
import com.okunu.basic.recyclerview.BaseRecyclerViewAdapter

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
class DefaultReposBridge : ReposBridgeInterface {

    override fun createReposAdapter(): BaseRecyclerViewAdapter =
        object : BaseRecyclerViewAdapter() {}

    override fun toReposActivity(context: Context) {
        Toast.makeText(context, "toReposActivity", Toast.LENGTH_LONG).show()
    }
}