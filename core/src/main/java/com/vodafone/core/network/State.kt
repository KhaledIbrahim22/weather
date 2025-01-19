package com.vodafone.core.network

sealed class State<out T> {
    data object Loading : State<Nothing>()
    class Success<out T>(val data: T) : State<T>()
    class Error(val message: String?) : State<Nothing>()
}
