package com.gabcode.core.domain.model

import com.gabcode.core.extension.empty

data class Item(
    val id: String,
    val title: String,
    val price: Int = 0,
    val thumbnail: String = String.empty(),
    val pictures: List<Picture> = listOf()
) {
    companion object {
        fun empty() = Item(String.empty(), String.empty(), 0, String.empty(), listOf())
    }
}

data class Picture(
    val id: String,
    val url: String
)


