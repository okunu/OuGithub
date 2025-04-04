package com.okunu.common.extensions

import android.text.Editable
import android.text.TextWatcher
import android.view.View

/**
 * Created by okunu on 2025-04-03.
 * Email : okunu@qq.com.
 */
inline fun createTextWatcher(
    crossinline afterTextChanged: (s: Editable?) -> Unit = {},
    crossinline beforeTextChanged: (s: CharSequence?, start: Int, count: Int, after: Int) -> Unit = { _, _, _, _ -> },
    crossinline onTextChanged: (s: CharSequence?, start: Int, before: Int, count: Int) -> Unit = { _, _, _, _ -> }
) = object : TextWatcher {

    override fun afterTextChanged(s: Editable?) {
        afterTextChanged(s)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        beforeTextChanged(s, start, count, after)
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        onTextChanged(s, start, before, count)
    }

}

fun View.visibleOrGone(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

