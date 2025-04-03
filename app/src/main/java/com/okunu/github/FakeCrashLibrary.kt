package com.okunu.github

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
object FakeCrashLibrary {

    fun log(priority: Int, tag: String?, message: String) {}

    fun logWarning(t: Throwable) {}

    fun logError(t: Throwable) {}
}