package com.gabcode.core.di

import com.gabcode.core.data.repository.ItemRepositoryImpl
import com.gabcode.core.domain.repository.ItemRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    internal abstract fun bindItemRepository(itemRepositoryImpl: ItemRepositoryImpl): ItemRepository
}