package com.gabcode.core.domain.repository

import com.gabcode.core.data.remote.Result
import com.gabcode.core.domain.model.Item
import com.gabcode.core.domain.model.SearchResult

interface ItemRepository {

    suspend fun searchQuery(query: String): Result<SearchResult<Item>>
}