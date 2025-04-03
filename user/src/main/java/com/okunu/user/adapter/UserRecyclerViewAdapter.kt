package com.okunu.user.adapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.okunu.basic.recyclerview.BaseRecyclerViewAdapter
import com.okunu.basic.recyclerview.BaseRecyclerViewVH
import com.okunu.basic.recyclerview.CommonRecyclerViewVH
import com.okunu.common.model.BaseRecyclerViewModel
import com.okunu.common.model.TYPE_INFO
import com.okunu.common.model.UserModel
import com.okunu.user.R
import com.okunu.user.BR
import com.okunu.user.databinding.UserItemUserInfoLayoutBinding
import com.okunu.user.vm.UserInfoVM


/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
class UserRecyclerViewAdapter(val vm: UserInfoVM) : BaseRecyclerViewAdapter() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerViewVH<ViewDataBinding, BaseRecyclerViewModel> {
        return when (viewType) {
            TYPE_INFO -> CommonRecyclerViewVH<UserItemUserInfoLayoutBinding, UserModel>(
                parent,
                R.layout.user_item_user_info_layout,
                vm,
                BR.vm
            )
            else -> super.onCreateViewHolder(parent, viewType)
        }
    }
}