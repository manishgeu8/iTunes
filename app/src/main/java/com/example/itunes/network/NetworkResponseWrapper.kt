package com.example.itunes.network

class NetworkResponseWrapper<T>(
    val status: Status,
    val response: T? = null,
    val error: Throwable? = null
) {

    sealed class Status {
        object SUCCESS : Status()
        object LOADING : Status()
        object ERROR : Status()
    }

    companion object {
        fun <T> loading() = NetworkResponseWrapper<T>(Status.LOADING)
        fun <T> success(data: T) = NetworkResponseWrapper(Status.SUCCESS, data)
        fun <T> error(err: Throwable) = NetworkResponseWrapper<T>(Status.ERROR, null, err)
    }
}