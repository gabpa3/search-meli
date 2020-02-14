package com.gabcode.core.data.remote

sealed class Failure(open val message: String?){
    object NetworkFailure : Failure(null)
    data class ServerFailure(override val message: String?) : Failure(message)
    object GenericFailure : Failure(null)
    object NoFoundDataFailure : Failure(null)
}