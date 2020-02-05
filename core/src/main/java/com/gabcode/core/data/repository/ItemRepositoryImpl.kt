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
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val service: ApiService
) : ItemRepository {

    private val networkHelper = NetworkHelper()

    init {
        val client = RestClient.createHttpClient()
        val retrofit = RestClient.createRetrofit("https://api.mercadolibre.com/", client)
//        service = RestClient.createService(retrofit, ApiService::class.java)
    }

    override suspend fun searchQuery(query: String): Result<SearchResult<Item>> {
        return  networkHelper.safeRequest(
            dispatcher, { service.search(query) }, { it.toDomainModel() })
    }

}