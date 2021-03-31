package dev.sertan.android.rovercamera.ui.result

import android.view.View
import dev.sertan.android.rovercamera.data.model.Photo

interface ResultRecyclerListener {
    fun photoClick(view: View, photo: Photo)
}