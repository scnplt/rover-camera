package dev.sertan.android.rovercamera.util.extensions

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

@BindingAdapter("is_refreshing")
fun SwipeRefreshLayout.isRefreshing(mIsRefreshing: Boolean) {
    isRefreshing = mIsRefreshing
}