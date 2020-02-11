package com.gabcode.search_meli.di

import coil.ImageLoader


interface ComponentProvider {
    val component: AppComponent
    val imageLoader: ImageLoader
}