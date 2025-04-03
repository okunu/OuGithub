package com.okunu.common.utils

import android.util.Log
import java.lang.Exception

object ALog {

    fun i(msg: String) {
        Log.i("ookunu", msg)
    }

    fun d(msg: String) {
        Log.d("ookunu", msg)
    }

    fun w(msg: String) {
        Log.w("ookunu", msg)
    }

    fun e(msg: String, exception: Exception) {
        Log.e("ookunu", msg, exception)
    }
}