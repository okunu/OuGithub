package com.okunu.componetbridge.following

import android.content.Context
import android.widget.Toast

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
class DefaultFollowingBridge : FollowingBridgeInterface {

    override fun toFollowingActivity(context: Context) {
        Toast.makeText(context, "toFollowingActivity", Toast.LENGTH_LONG).show()
    }
}