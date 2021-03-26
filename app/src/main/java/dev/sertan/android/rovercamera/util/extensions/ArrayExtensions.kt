package dev.sertan.android.rovercamera.util.extensions

fun Array<out String?>.isAllBlankOrNull(): Boolean {
    for (text in this) if (!text.isNullOrBlank()) return false
    return true
}