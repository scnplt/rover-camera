package dev.sertan.android.rovercamera.util.extensions

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("isVisible")
fun View.isVisible(isShow: Boolean) {
    this.visibility = if (isShow) View.VISIBLE else View.GONE
}
