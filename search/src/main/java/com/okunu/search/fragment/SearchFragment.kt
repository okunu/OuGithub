package com.okunu.search.fragment


import com.okunu.basic.fragment.BaseFragment
import com.okunu.search.R
import com.okunu.search.BR
import com.okunu.search.databinding.SearchFragmentSearchLayoutBinding
import com.okunu.search.vm.SearchVM

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
class SearchFragment : BaseFragment<SearchFragmentSearchLayoutBinding, SearchVM>() {

    override fun getVariableId(): Int = BR.vm

    override fun getLayoutId(): Int = R.layout.search_fragment_search_layout

    override fun getViewModelInstance(): SearchVM = SearchVM()

    override fun getViewModelClass(): Class<SearchVM> = SearchVM::class.java

    companion object {
        fun getInstance(): SearchFragment {
            return SearchFragment()
        }
    }
}