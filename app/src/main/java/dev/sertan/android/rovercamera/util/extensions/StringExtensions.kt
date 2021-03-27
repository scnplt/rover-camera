package dev.sertan.android.rovercamera.util.extensions

fun String?.toIntOrZero(): Int = try {
    this!!.toInt()
} catch (e: Exception) {
    0
}

fun String.httpToHttps(): String {
    val http = "http://"
    val index = indexOf(http)
    if (index == -1) return this
    return "https://${substring(index + http.length)}"
}