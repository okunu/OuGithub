package com.okunu.common.utils

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.text.TextUtils
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import java.lang.reflect.InvocationTargetException

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
object CommonUtils {
    private var mApplication: Application? = null

    fun getUsername(): String = SPUtils.getString(SPUtils.KEY_USER_NAME) ?: ""

    fun getRealUserName(): String = SPUtils.getString(SPUtils.KEY_REAL_USER_NAME) ?: ""

    fun getPassword(): String = SPUtils.getString(SPUtils.KEY_PASSWORD) ?: ""

    fun isBasicLogin() = !TextUtils.isEmpty(SPUtils.getString(SPUtils.KEY_AUTHORIZATION_BASIC))

    fun hasToken() = !TextUtils.isEmpty(SPUtils.getString(SPUtils.KEY_ACCESS_TOKEN))

    fun hasLogin() = isBasicLogin() || hasToken()

//    fun getAuthorization() = if (isBasicLogin()) "Basic " + App.AUTHORIZATION_BASIC else "token " + App.ACCESS_TOKEN

    fun parseToJsonObject(src: Any): JsonObject =
        JsonParser().parse(Gson().toJson(src)).asJsonObject

    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5).toInt()
    }

    fun initApp(app: Application) {
        mApplication = app
    }

    @SuppressLint("PrivateApi")
    fun getApp(): Application {
        try {
            return mApplication.takeIf { mApplication != null }
                ?: Class.forName("android.app.ActivityThread").run {
                    val currentActivityThread = getMethod("currentActivityThread").invoke(null)
                    val application =
                        getMethod("getApplication").invoke(currentActivityThread) as Application
                    initApp(application)
                    return application
                }

        } catch (e: NoSuchMethodException) {
            ALog.e("getApp:", e)
        } catch (e: IllegalAccessException) {
            ALog.e("getApp:", e)
        } catch (e: InvocationTargetException) {
            ALog.e("getApp:", e)
        } catch (e: ClassNotFoundException) {
            ALog.e("getApp:", e)
        }
        throw NullPointerException("App should init ")
    }
}