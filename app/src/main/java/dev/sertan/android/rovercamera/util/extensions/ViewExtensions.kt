package dev.sertan.android.rovercamera.util.extensions

import android.view.View
import android.view.animation.AnimationUtils
import androidx.databinding.BindingAdapter

@BindingAdapter("is_visible")
fun View.isVisible(isShow: Boolean) { visibility = if (isShow) View.VISIBLE else View.GONE }

fun View.loadAndStartAnimation(animId: Int){
    val anim = AnimationUtils.loadAnimation(this.context, animId)
    startAnimation(anim)
}