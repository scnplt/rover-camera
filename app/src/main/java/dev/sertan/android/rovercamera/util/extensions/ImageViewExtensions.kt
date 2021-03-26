package dev.sertan.android.rovercamera.util.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import dev.sertan.android.rovercamera.R

@BindingAdapter("loadFromUrl")
fun ImageView.loadFromUrl(url: String) = Glide.with(this)
    .load(url.httpToHttps())
    .centerCrop()
    .placeholder(R.drawable.ic_refresh)
    .error(R.drawable.ic_error)
    .into(this)