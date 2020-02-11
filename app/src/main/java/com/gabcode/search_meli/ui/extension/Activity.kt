package com.gabcode.search_meli.ui.extension

import android.app.Activity
import com.gabcode.search_meli.di.ComponentProvider

val Activity.injector get() =
    (application as ComponentProvider).component

val Activity.imageLoader get() =
    (application as ComponentProvider).imageLoader