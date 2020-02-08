package com.gabcode.core.data.remote

sealed class Result<out T> {
    data class Success<out T>(val data: T): Result<T>()
    data class Error(val message: String?): Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[message=$message]"
        }
    }
}