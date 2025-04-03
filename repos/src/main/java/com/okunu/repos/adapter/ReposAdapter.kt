package com.okunu.repos.adapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.okunu.repos.vm.ReposVHVM
import com.okunu.basic.recyclerview.BaseRecyclerViewAdapter
import com.okunu.basic.recyclerview.BaseRecyclerViewVH
import com.okunu.basic.recyclerview.CommonRecyclerViewVH
import com.okunu.common.model.BaseRecyclerViewModel
import com.okunu.common.model.ReposModel
import com.okunu.repos.R
import com.okunu.repos.BR
import com.okunu.repos.databinding.ReposItemReposLayoutBinding

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
class ReposAdapter : BaseRecyclerViewAdapter() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerViewVH<ViewDataBinding, BaseRecyclerViewModel> {
        return CommonRecyclerViewVH<ReposItemReposLayoutBinding, ReposModel>(
            parent,
            R.layout.repos_item_repos_layout,
            ReposVHVM(parent.context),
            BR.vm
        )
    }
}