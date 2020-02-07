package com.gabcode.core.domain.usecase

import com.gabcode.core.domain.model.Item
import com.gabcode.core.data.remote.Result
import com.gabcode.core.domain.repository.ItemRepository
import javax.inject.Inject

class GetItemDataUseCase @Inject constructor(private val repository: ItemRepository) {

    suspend operator fun invoke(itemId: String): Result<Item> {
        return repository.getItem(itemId)
    }
}



