package com.gabcode.search_meli.di

import android.app.Application
import com.gabcode.core.di.DataModule
import com.gabcode.core.di.DomainModule
import com.gabcode.core.di.RepositoryModule
import com.gabcode.search_meli.di.viewmodel.ViewModelModule
import com.gabcode.search_meli.ui.detail.ItemDetailActivity
import com.gabcode.search_meli.ui.home.HomeActivity
import com.gabcode.search_meli.ui.search.SearchActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ViewModelModule::class,
//        DomainModule::class,
        RepositoryModule::class,
        DataModule::class
    ]
//    dependencies = [CoreComponent::class]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): AppComponent
    }

    fun inject(homeActivity: HomeActivity)
    fun inject(searchActivity: SearchActivity)
    fun inject(itemDetailActivity: ItemDetailActivity)
}

