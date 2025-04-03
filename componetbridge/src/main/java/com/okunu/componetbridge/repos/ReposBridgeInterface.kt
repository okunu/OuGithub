package com.okunu.componetbridge.repos

import android.content.Context
import com.okunu.basic.recyclerview.BaseRecyclerViewAdapter
import com.okunu.componetbridge.BridgeInterface

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
interface ReposBridgeInterface: BridgeInterface {

    fun toReposActivity(context: Context)

    fun createReposAdapter(): BaseRecyclerViewAdapter
}