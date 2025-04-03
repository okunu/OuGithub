package com.okunu.common

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
sealed class ToPageStatus

object PageDefault : ToPageStatus()
object WelcomeToMain : ToPageStatus()
object WelcomeToLogin : ToPageStatus()
object LoginToMain : ToPageStatus()
object LoginToAuthorize: ToPageStatus()
object UserToRepos: ToPageStatus()
object UserToFollowers: ToPageStatus()
object UserToFollowing: ToPageStatus()
object ReposToWebView: ToPageStatus()
