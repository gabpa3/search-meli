package com.gabcode.core.domain.usecase

import com.gabcode.core.domain.model.Item
import com.gabcode.core.domain.repository.ItemRepository
import com.gabcode.core.data.remote.Result
import com.gabcode.core.domain.model.SearchResult
import javax.inject.Inject

class SearchDataUseCase @Inject constructor(private val repository: ItemRepository) {

    suspend operator fun invoke(query: String): Result<SearchResult<Item>> {
        return repository.searchQuery(query)
    }

}