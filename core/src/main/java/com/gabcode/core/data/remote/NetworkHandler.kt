package com.gabcode.core.data.remote

import android.content.Context
import com.gabcode.core.extension.networkState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkHandler @Inject constructor(private val context: Context) {
    val isConnected get() = context.networkState?.isConnected
}