package com.gabcode.core.data.repository

import com.gabcode.core.data.mapper.toDomainModel
import com.gabcode.core.data.remote.*
import com.gabcode.core.domain.model.Item
import com.gabcode.core.domain.model.SearchResult
import com.gabcode.core.domain.repository.ItemRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val networkHandler: NetworkHandler,
    private val dispatcher: CoroutineDispatcher,
    private val service: ApiService
) : ItemRepository {

    override suspend fun searchQuery(query: String): Result<SearchResult<Item>> {
        return  NetworkHelper.safeRequest(
            dispatcher, { service.search(query) }, { it.toDomainModel() })
    }

    override suspend fun getItem(id: String): Result<Item> {
        return NetworkHelper.safeRequest(
            dispatcher, { service.getItem(id) }, { it.toDomainModel() }
        )
    }

}