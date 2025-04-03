package com.okunu.repos

import com.alibaba.android.arouter.facade.annotation.Route
import com.okunu.basic.activity.BaseActivity
import com.okunu.common.ARouterPaths
import com.okunu.repos.databinding.ReposActivityMainLayoutBinding

import com.okunu.repos.vm.ReposVM

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
@Route(path = ARouterPaths.PATH_REPOS_REPOS)
class ReposActivity : BaseActivity<ReposActivityMainLayoutBinding, ReposVM>() {

    override fun getVariableId(): Int = BR.vm

    override fun getLayoutId(): Int = R.layout.repos_activity_main_layout

    override fun getViewModelInstance(): ReposVM = ReposVM()

    override fun getViewModelClass(): Class<ReposVM> = ReposVM::class.java

}