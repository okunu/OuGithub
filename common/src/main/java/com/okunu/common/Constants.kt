package com.okunu.common

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
object Constants {
    const val AUTHORIZATION_CODE = "code"
    const val CLIENT_ID = "Ov23liPJXWmOa05T5cfe"
    const val CLIENT_SECRET = "a052b3e68c6e3c0b4e83eb7798c00bf9ae237e4d"
    const val REDIRECT_URI = "github://login"

    const val AUTHORIZATION_URL =
        "https://github.com/login/oauth/authorize?client_id=$CLIENT_ID&redirect_uri=$REDIRECT_URI"
}