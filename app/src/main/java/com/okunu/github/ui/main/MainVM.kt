package com.okunu.github.ui.main

//import com.okunu.common.model.IssuesRequestModel


import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.viewpager.widget.PagerAdapter
import com.okunu.basic.BaseVM
import com.okunu.common.utils.ALog
import com.okunu.github.R

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
class MainVM(private val fm: FragmentManager) : BaseVM() {

    companion object {
        private const val HOME_POS = 0
        private const val USER_POS = 1
    }

    private val mMainViewPagerAdapter by lazy { MainViewPagerAdapter(fm) }
    private var mBackTime = 0L
    private var mBackIntervalTime = 2500L
    val selectedPosition = MutableLiveData<Int>(HOME_POS)

    override fun attach(savedInstanceState: Bundle?) {
    }

    fun createAdapter(): PagerAdapter = mMainViewPagerAdapter

    fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        ALog.i("main onNavigationItemSelected")
        when (menuItem.itemId) {
            R.id.home -> selectedPosition.value = HOME_POS
            R.id.user -> selectedPosition.value = USER_POS
            else -> return false

        }
        return true
    }

    fun goSearchPage() {
        selectedPosition.value = HOME_POS
    }

    fun exitApp(): Boolean {
        if (System.currentTimeMillis() - mBackTime <= mBackIntervalTime) {
            return true
        }
        mBackTime = System.currentTimeMillis()
        return false
    }

}