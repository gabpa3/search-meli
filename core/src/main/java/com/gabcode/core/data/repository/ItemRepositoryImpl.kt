package com.gabcode.core.data.repository

import com.gabcode.core.data.mapper.toDomainModel
import com.gabcode.core.data.remote.ApiService
import com.gabcode.core.data.remote.NetworkHelper
import com.gabcode.core.data.remote.RestClient
import com.gabcode.core.data.remote.Result
import com.gabcode.core.domain.model.Item
import com.gabcode.core.domain.model.SearchResult
import com.gabcode.core.domain.repository.ItemRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class ItemRepositoryImpl(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ItemRepository {

    private lateinit var service: ApiService

    private val networkHelper = NetworkHelper()

    init {
        val client = RestClient.createClient()
        val retrofit = RestClient.createRetrofit("https://api.mercadolibre.com/", client)
        service = RestClient.createService(retrofit, ApiService::class.java)
    }

    override suspend fun searchQuery(query: String): Result<SearchResult<Item>> {
        return  networkHelper.safeRequest(
            dispatcher, { service.search(query) }, { it.toDomainModel() })
    }

}