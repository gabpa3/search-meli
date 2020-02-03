package com.gabcode.core.data.remote

import com.gabcode.core.data.remote.model.ApiErrorResponse
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response

class NetworkHelper {

    suspend fun <T,R> safeRequest(
        dispatcher: CoroutineDispatcher, call: suspend () -> Response<T>,  transform: (T) -> R): Result<R> {
        return withContext(dispatcher) {
            try {
                val response = call.invoke()
                if (response.isSuccessful) {
                    response.body()?.let {
                        Result.Success(transform(it))
                    }
                }
                Result.Error(response.message())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> convertErrorBody(throwable)

                }

                Result.Error(throwable.message ?: throwable.toString())
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