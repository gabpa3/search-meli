package com.gabcode.core.domain.usecase

import com.gabcode.core.domain.model.RecentSearch
import com.gabcode.core.data.remote.Result
import com.gabcode.core.domain.repository.ItemRepository
import javax.inject.Inject

class GetRecentSearchDataUseCase @Inject constructor(private val repository: ItemRepository) {

    suspend operator fun invoke(): Result<RecentSearch> {
        return repository.getRecentItemSearched()
    }
}