package dev.sertan.android.rovercamera.util.extensions

fun String?.toIntOrZero(): Int = try { this!!.toInt() } catch (e: Exception) { 0 }

fun String.httpToHttps() : String {
    val http = "http://"
    val index = this.indexOf(http)
    if (index == -1) return this
    return "https://${this.substring(index + http.length)}"
}