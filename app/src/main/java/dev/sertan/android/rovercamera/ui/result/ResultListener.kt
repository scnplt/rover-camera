package dev.sertan.android.rovercamera.ui.result

import android.content.Context
import android.content.Intent
import android.net.Uri

object ResultListener {
    fun openImageWithUrl(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }
}