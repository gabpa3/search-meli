package com.gabcode.search_meli.ui

import android.app.Application
import coil.ImageLoader
import com.gabcode.search_meli.di.AppComponent
import com.gabcode.search_meli.di.ComponentProvider
import com.gabcode.search_meli.di.DaggerAppComponent

class App : Application(), ComponentProvider {

    override val component: AppComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }

    override val imageLoader: ImageLoader by lazy {
        ImageLoader.Builder(applicationContext)
            .availableMemoryPercentage(0.5)
            .bitmapPoolPercentage(0.5)
            .crossfade(true)
            .build()
    }

}