package dev.sertan.android.rovercamera.util

sealed class State {
    object IDLE : State()
    object LOADING : State()
    data class ERROR(val msg: String) : State()
    data class LOADED<T>(val data: T?) : State()
}
