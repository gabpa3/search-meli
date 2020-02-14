package com.gabcode.core.data.repository

import com.gabcode.core.data.mapper.toDomainModel
import com.gabcode.core.data.remote.*
import com.gabcode.core.domain.model.Item
import com.gabcode.core.domain.model.RecentSearch
import com.gabcode.core.domain.model.SearchResult
import com.gabcode.core.domain.repository.ItemRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val networkHandler: NetworkHandler,
    private val dispatcher: CoroutineDispatcher,
    private val service: ApiService
) : ItemRepository {

    override suspend fun searchQuery(query: String, offset: Int): Result<SearchResult<Item>> {
        return when (networkHandler.isConnected) {
            true -> NetworkHelper.safeRequest(
                dispatcher, { service.search(query, offset) }, { it.toDomainModel() })
            false, null -> Result.Error(Failure.NetworkFailure)
        }
    }

    override suspend fun getItem(id: String): Result<Item> {
        return when (networkHandler.isConnected) {
            true -> NetworkHelper.safeRequest(
                dispatcher, { service.getItem(id) }, { it.toDomainModel() })
            false, null -> Result.Error(Failure.NetworkFailure)
        }
    }

    override suspend fun getRecentItemSearched(): Result<RecentSearch> {
        val result: List<Item> = listOf(
            Item(id = "1", title = "Motorola"),
            Item(id = "2", title = "Zapatos nike"),
            Item(id = "3", title = "adidas advantage")
        )

        return Result.Success(RecentSearch(result))
    }

}