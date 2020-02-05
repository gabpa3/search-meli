package com.gabcode.core.di

import android.app.Activity

interface BaseComponent<T> {
    fun inject(target: T)
}
