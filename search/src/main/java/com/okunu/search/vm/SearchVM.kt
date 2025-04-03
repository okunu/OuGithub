package com.okunu.search.vm

import android.R
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.okunu.basic.BaseVM
import com.okunu.common.extensions.RequestCallback
import com.okunu.common.model.ResponseError
import com.okunu.common.model.ResponseSuccess
import com.okunu.common.model.SearchModel
import com.okunu.common.utils.ALog
import com.okunu.common.utils.CommonUtils
import com.okunu.componetbridge.provider.BridgeProviders
import com.okunu.componetbridge.repos.ReposBridgeInterface
import com.okunu.network.HttpClient


import com.okunu.search.repository.SearchRepository

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
class SearchVM : BaseVM() {

    private val repository = SearchRepository(HttpClient.getService(), viewModelScope)
    private val mAdapter =
        BridgeProviders.instance.getBridge(ReposBridgeInterface::class.java).createReposAdapter()

    val clearFocus = MutableLiveData<Boolean>(true)
    val selectedLanguage = MutableLiveData<String>("All")
    val selectedStar = MutableLiveData<String>("desc")

    override fun attach(savedInstanceState: Bundle?) {
        search("android-api-analysis")
    }

    private fun search(query: String?) {
        query?.let {
            showLoading.value = true
            val realQuery = listOfNotNull(
                it.takeIf { it.isNotBlank() },
                selectedLanguage.value?.let { l -> "language:$l" }
            ).joinToString(" ")
            ALog.i("realQ: $realQuery")
            ALog.i("search vm  star: ${selectedStar.value}  ${selectedLanguage.value}")
            repository.searchRepository(realQuery, selectedStar.value ?: "desc", object : RequestCallback<SearchModel> {
                override fun onSuccess(result: ResponseSuccess<SearchModel>) {
                    showLoading.value = false
                    getAdapter().clear()
                    result.data?.let { searchModel ->
                        getAdapter().addData(searchModel.items)
                    }
                }

                override fun onError(error: ResponseError) {
                    showLoading.value = false
                }
            })
        }
    }

    fun getAdapter() = mAdapter

    fun setLanguageSpinnerAdapter(): ArrayAdapter<String> {
        val languages = arrayOf("All", "Kotlin", "Java", "Python", "JavaScript", "C", "C++")
        val adapter = ArrayAdapter(CommonUtils.getApp(), R.layout.simple_spinner_item, languages)
        return adapter
    }

    fun setStarSpinnerItemSelectedListener() = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>, view: android.view.View, position: Int, id: Long) {
            selectedStar.value = parent.getItemAtPosition(position) as String
        }
        override fun onNothingSelected(parent: AdapterView<*>) {
            selectedStar.value = "desc"
        }
    }

    fun setLanguageSpinnerItemSelectedListener() = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>, view: android.view.View, position: Int, id: Long) {
            selectedLanguage.value = parent.getItemAtPosition(position) as String
        }
        override fun onNothingSelected(parent: AdapterView<*>) {
            selectedLanguage.value = "All"
        }
    }

    fun setStarSpinnerAdapter(): ArrayAdapter<String> {
        val star = arrayOf("desc", "asc")
        return ArrayAdapter(CommonUtils.getApp(), R.layout.simple_spinner_item, star)
    }

    fun setOnQueryTextListener() = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            clearFocus.value = true
            searchSubmit(query)
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            clearFocus.value = false
            return true
        }
    }

    private fun searchSubmit(query: String?) {
        search(query)
    }
}