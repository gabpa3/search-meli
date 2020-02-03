package com.gabcode.core.domain.model

data class SearchResult<T>(
    val paging: Paging,
    val results: List<T>
) {
    companion object {
        fun <T> empty(): SearchResult<T> {
            return SearchResult(Paging.empty(), listOf())
        }
    }
}

data class Paging(
    val total: Int,
    val offset: Int,
    val limit: Int
) {
    companion object {
        fun empty(): Paging {
            return Paging(0, 0, 0)
        }
    }
}
