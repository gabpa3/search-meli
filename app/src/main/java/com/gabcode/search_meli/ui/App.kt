package com.gabcode.search_meli.ui

import android.app.Application
import com.gabcode.search_meli.di.AppComponent
import com.gabcode.search_meli.di.ComponentProvider

class App : Application(), ComponentProvider {

    override val component: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }


}