package com.okunu.common.extensions

import com.okunu.common.model.ResponseError
import com.okunu.common.model.ResponseSuccess
import com.okunu.common.utils.ALog
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
fun <T> request(
    scope: CoroutineScope,
    callback: RequestCallback<T>? = null,
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    handler: CoroutineExceptionHandler? = null,
    block: suspend CoroutineScope.() -> T
): Job = scope.launch(dispatcher) {
    try {
        val result = block()
        withContext(Dispatchers.Main) {
            callback?.onSuccess(ResponseSuccess(result))
        }
    } catch (e: Exception) {
        e.printStackTrace()
        ALog.e("Http Request Exception message %s", Exception(e.message))
        withContext(Dispatchers.Main) {
            handler?.handleException(this.coroutineContext, e)
            callback?.onError(ResponseError(e))
        }
    }
}

interface RequestCallback<T> {
    fun onSuccess(result: ResponseSuccess<T>)
    fun onError(error: ResponseError)
}
