package com.okunu.repos

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.okunu.basic.activity.BaseActivity
import com.okunu.common.ARouterPaths
import com.okunu.common.utils.ALog
import com.okunu.repos.databinding.ReposCreateIssueActivityLayoutBinding
import com.okunu.repos.vm.CreateIssueVM

@Route(path = ARouterPaths.PATH_USER_SUBMIT_ISSUE)
class CreateIssueActivity: BaseActivity<ReposCreateIssueActivityLayoutBinding, CreateIssueVM>()  {

    override fun getVariableId(): Int = BR.vm

    override fun getLayoutId(): Int = R.layout.repos_create_issue_activity_layout

    override fun getViewModelInstance(): CreateIssueVM = CreateIssueVM()

    override fun getViewModelClass(): Class<CreateIssueVM> = CreateIssueVM::class.java

    @Autowired
    lateinit var repoName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        ALog.i("CreateIssueActivity onCreate $repoName")
        viewModel.repoName.value = repoName
    }
}

