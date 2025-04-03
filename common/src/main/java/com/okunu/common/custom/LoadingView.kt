package com.okunu.common.custom

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.okunu.common.utils.CommonUtils
import com.okunu.common.R

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
class LoadingView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        gravity = Gravity.CENTER
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        setBackgroundColor(Color.TRANSPARENT)
        addView(ImageView(context).apply {

            Glide.with(context).load(R.drawable.loading).apply(RequestOptions().apply {
                override(
                    CommonUtils.dip2px(context, 64f),
                    CommonUtils.dip2px(context, 64f)
                ).centerCrop()
            }).into(this)
        })
    }
}