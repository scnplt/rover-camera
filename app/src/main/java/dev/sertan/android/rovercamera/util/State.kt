package dev.sertan.android.rovercamera.util

sealed class State {
    object LOADING : State()
    object ERROR : State()
    data class LOADED<T>(val data: T?) : State()
}
