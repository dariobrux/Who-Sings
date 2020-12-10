package com.dariobrux.whosings.common

/**
 *
 * Created by Dario Bruzzese on 7/12/2020.
 *
 */
data class Resource<out T>(val status: Status, val data: T?, val message: String?) {

    enum class Status {
        NONE,
        LOADING,
        SUCCESS,
        ERROR
    }

    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, message)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }

    fun isSuccess() = status == Status.SUCCESS && data != null
}