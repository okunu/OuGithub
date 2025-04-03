package com.okunu.user.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.okunu.basic.fragment.BaseFragment
import com.okunu.common.UserToRepos
import com.okunu.common.utils.ALog
import com.okunu.componetbridge.provider.BridgeProviders
import com.okunu.componetbridge.repos.ReposBridgeInterface
import com.okunu.user.BR
import com.okunu.user.R
import com.okunu.user.databinding.UserFragmentUserBinding
import com.okunu.user.vm.UserInfoVM
import com.okunu.user.vm.UserVM

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
class UserFragment : BaseFragment<UserFragmentUserBinding, UserVM>() {

    override fun getVariableId(): Int = BR.vm

    override fun getLayoutId(): Int = R.layout.user_fragment_user

    override fun getViewModelInstance(): UserVM = UserVM(UserInfoVM())

    override fun getViewModelClass(): Class<UserVM> = UserVM::class.java

    private lateinit var rootView: View

    companion object {
        fun getInstance(): UserFragment {
            return UserFragment()
        }
    }

    fun refreshData() {

    }

    override fun onResume() {
        super.onResume()
        ALog.i("user fragment on resume")
        viewModel.getUser(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        if (view != null) {
            rootView = view
        }
        return view
    }

    override fun addObserver() {
        super.addObserver()
        viewModel.userInfoVM.navigate.observe(this, Observer {
            when (it) {
                is UserToRepos -> BridgeProviders.instance.getBridge(ReposBridgeInterface::class.java)
                    .toReposActivity(requireContext())
                else -> {}
            }
        })
    }
}