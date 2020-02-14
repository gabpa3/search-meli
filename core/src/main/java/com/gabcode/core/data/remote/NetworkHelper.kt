package com.gabcode.core.data.remote

import com.gabcode.core.data.remote.model.ApiErrorResponse
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

object NetworkHelper {

    suspend fun <T,R> safeRequest(
        dispatcher: CoroutineDispatcher, call: suspend () -> Response<T>,  transform: (T) -> R): Result<R> {
        return withContext(dispatcher) {
            try {
                val response = call.invoke()
                when (response.isSuccessful) {
                    true -> Result.Success(transform(response.body()!!))
                    false -> Result.Error(Failure.ServerFailure(response.errorBody().toString()))
                }
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> Result.Error(Failure.NetworkFailure)
                    is HttpException -> convertErrorBody(throwable)
//                    else -> Result.Error.GenericFailure
                }

                Result.Error(Failure.GenericFailure)
            }
        }
    }

    private fun convertErrorBody(throwable: HttpException): ApiErrorResponse? {
        throwable.response()?.errorBody()?.source()?.let {
            val gson = GsonBuilder().create() // TODO adapter response
            return ApiErrorResponse("", "",0)
        }

        return null
    }
}