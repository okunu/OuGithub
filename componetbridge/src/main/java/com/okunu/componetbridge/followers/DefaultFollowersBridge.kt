package com.okunu.componetbridge.followers

import android.content.Context
import android.widget.Toast

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
class DefaultFollowersBridge : FollowersBridgeInterface {

    override fun toFollowersActivity(context: Context) {
        Toast.makeText(context, "toFollowersActivity", Toast.LENGTH_LONG).show()
    }
}