package com.okunu.common.utils

import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.okunu.common.R
import com.okunu.common.navigation.CommonNavigationLayout
import com.okunu.common.navigation.OnNavigationListener


/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
object BindingAdapter {

    @JvmStatic
    @BindingAdapter("android:visibility")
    fun setVisible(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("addTextChangedListener1")
    fun addTextChangedListener(view: EditText, textWatcher: TextWatcher?) {
        textWatcher?.let {
            view.addTextChangedListener(it)
        }
    }

    @JvmStatic
    @BindingAdapter("actionListener")
    fun addActionListener(view: EditText, actionListener: TextView.OnEditorActionListener) {
        view.setOnEditorActionListener(actionListener)
    }

    // -------- ViewPager start --------
    @JvmStatic
    @BindingAdapter("adapter")
    fun setAdapter(viewPager: ViewPager, adapter: PagerAdapter) {
        viewPager.adapter = adapter
    }

    @JvmStatic
    @BindingAdapter("addOnPageChangedListener")
    fun addOnPageChangedListener(viewPager: ViewPager, listener: ViewPager.OnPageChangeListener) {
        viewPager.addOnPageChangeListener(listener)
    }

    @JvmStatic
    @BindingAdapter("setCurrentItem")
    fun setCurrentItem(viewPager: ViewPager, pos: Int) {
        viewPager.setCurrentItem(pos, false)
    }

    @JvmStatic
    @BindingAdapter("setOffscreenPageLimit")
    fun setOffscreenPageLimit(viewPager: ViewPager, offscreenPageLimit: Int) {
        viewPager.offscreenPageLimit = offscreenPageLimit
    }
    // -------- ViewPager end --------

    @JvmStatic
    @BindingAdapter("adapter")
    fun setAdapter(
        recyclerView: RecyclerView,
        adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>
    ) {
        recyclerView.adapter = adapter
    }

    @JvmStatic
    @BindingAdapter("setOnNavigationItemSelectedListener")
    fun setOnNavigationItemSelectedListener(
        bottomNavigationView: BottomNavigationView,
        listener: BottomNavigationView.OnNavigationItemSelectedListener
    ) {
        bottomNavigationView.setOnNavigationItemSelectedListener(listener)
    }

    // -------- ImageView start --------

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun setImageRes(imageView: ImageView, url: String?) {
        Glide.with(imageView).applyDefaultRequestOptions(RequestOptions().apply {
            placeholder(R.drawable.placeholder)
            centerCrop()
        }).load(url).into(imageView)
    }

    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageResource(imageView: ImageView, resId: Int) {
        imageView.setImageResource(resId)
    }

    // -------- ImageView end --------

    // -------- RefreshLayout start --------

    @JvmStatic
    @BindingAdapter("isEnabled")
    fun setEnabled(refreshLayout: SwipeRefreshLayout, enabled: Boolean) {
        refreshLayout.isEnabled = enabled
    }

    @JvmStatic
    @BindingAdapter("setOnRefreshListener")
    fun setListener(
        refreshLayout: SwipeRefreshLayout,
        listener: SwipeRefreshLayout.OnRefreshListener
    ) {
        refreshLayout.setOnRefreshListener(listener)
    }

    @JvmStatic
    @BindingAdapter("isRefreshing")
    fun setRefreshing(refreshLayout: SwipeRefreshLayout, refreshing: Boolean) {
        refreshLayout.isRefreshing = refreshing
    }

    // -------- RefreshLayout end --------

    // -------- SearchView start --------

    @JvmStatic
    @BindingAdapter("setOnSearchClickListener")
    fun setOnSearchClickListener(searchView: SearchView, listener: View.OnClickListener) {
        searchView.setOnSearchClickListener(listener)
    }

//    @JvmStatic
//    @BindingAdapter(value = ["onQueryTextSubmit", "onQueryTextChange"], requireAll = false)
//    fun setOnQueryTextListener(
//        searchView: SearchView,
//        submit: (String?) -> Unit = {},
//        change: (String?) -> Unit? = {}
//    ) {
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                submit(query)
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                change(newText)
//                return true
//            }
//        })
//    }

    @JvmStatic
    @BindingAdapter("setOnQueryTextListener")
    fun setOnQueryTextListener(
        searchView: SearchView,
        listener: SearchView.OnQueryTextListener
    ) {
        searchView.setOnQueryTextListener(listener)
    }

    @JvmStatic
    @BindingAdapter("setSpinnerAdapter")
    fun setSpinnerAdapter(sp: Spinner, adapter: ArrayAdapter<String>) {
        sp.adapter = adapter
    }

    @JvmStatic
    @BindingAdapter("setSpinnerSelectedListener")
    fun setSpinnerSelectedListener(sp: Spinner, listener: AdapterView.OnItemSelectedListener) {
        sp.onItemSelectedListener = listener
    }

    @JvmStatic
    @BindingAdapter("clearFocus")
    fun setClearFocus(searchView: SearchView, clearFocus: Boolean) {
        if (clearFocus) {
            searchView.clearFocus()
        }
    }


    // -------- SearchView end --------

    // -------- WebView start --------

    @JvmStatic
    @BindingAdapter("url")
    fun setWebViewUrl(webView: WebView, url: String) {
        if (!TextUtils.isEmpty(url)) {
            webView.loadUrl(url)
        }
    }

    @JvmStatic
    @BindingAdapter("webViewClient")
    fun setWebViewClient(webView: WebView, webViewClient: WebViewClient) {
        webView.webViewClient = webViewClient
    }

    @JvmStatic
    @BindingAdapter("webChromeClient")
    fun setWebChromeClient(webView: WebView, webViewClient: WebChromeClient) {
        webView.webChromeClient = webViewClient
    }

    // -------- WebView end --------

    // -------- CommonNavigationLayout start --------
    @JvmStatic
    @BindingAdapter("onNavigationListener")
    fun setOnNavigationListener(
        navigationLayout: CommonNavigationLayout,
        listener: OnNavigationListener
    ) {
        navigationLayout.setNavigationListener(listener)
    }
    // -------- CommonNavigationLayout end --------
}

