package com.okunu.user.vm

import com.okunu.basic.BaseRecyclerVM
import com.okunu.common.ToPageStatus
import com.okunu.common.UserToFollowers
import com.okunu.common.UserToFollowing
import com.okunu.common.UserToRepos
import com.okunu.common.live.SingleLiveEvent
import com.okunu.common.model.UserModel


/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
class UserInfoVM : BaseRecyclerVM<UserModel>() {

    var data: UserModel? = null
    var navigate: SingleLiveEvent<ToPageStatus> = SingleLiveEvent()

    override fun onBind(model: UserModel?) {
        data = model
    }

    fun reposClick() {
        navigate.value = UserToRepos
    }

    fun followersClick() {
        navigate.value = UserToFollowers
    }

    fun followingClick() {
        navigate.value = UserToFollowing
    }
}