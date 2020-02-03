package com.gabcode.core.data.remote.model

data class ApiErrorResponse(
    val message: String,
    val error: String,
    val status: Int
)