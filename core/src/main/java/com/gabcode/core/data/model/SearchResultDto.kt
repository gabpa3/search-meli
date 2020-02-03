package com.gabcode.core.data.model

import com.google.gson.annotations.SerializedName

data class SearchResultDto<T>(
    @SerializedName("query") val query: String?,
    @SerializedName("pagingDto") val pagingDto: PagingDto?,
    @SerializedName("results") val results: List<T>
)

data class PagingDto(
    @SerializedName("total") val total: Int,
    @SerializedName("offset") val offset: Int,
    @SerializedName("limit") val limit: Int
)