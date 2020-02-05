package com.gabcode.core.di

import com.gabcode.core.domain.repository.ItemRepository
import com.gabcode.core.domain.usecase.SearchDataUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideSearchDataUseCase(itemRepository: ItemRepository): SearchDataUseCase
            = SearchDataUseCase(itemRepository)
}