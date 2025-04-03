package com.okunu.basic.recyclerview

import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.okunu.common.model.BaseRecyclerViewModel

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
abstract class BasePagedListAdapter<T : BaseRecyclerViewModel>(
    callback: DiffUtil.ItemCallback<T> = object : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
            this.areItemsTheSame(oldItem, newItem)

        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
            this.areContentsTheSame(oldItem, newItem)

    },
    areItemsTheSame: (oldItem: T, newItem: T) -> Boolean,
    areContentsTheSame: (oldItem: T, newItem: T) -> Boolean
) : PagedListAdapter<T, BaseRecyclerViewVH<ViewDataBinding, BaseRecyclerViewModel>>(callback) {

    override fun onBindViewHolder(holder: BaseRecyclerViewVH<ViewDataBinding, BaseRecyclerViewModel>, position: Int) {
        holder.bind(getItem(position))
    }

}
