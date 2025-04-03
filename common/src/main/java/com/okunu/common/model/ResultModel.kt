package com.okunu.common.model

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
sealed class ResponseResult

data class ResponseSuccess<T>(val data: T?) : ResponseResult()
data class ResponseError(val t: Throwable?) : ResponseResult()