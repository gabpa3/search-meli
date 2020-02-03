package com.gabcode.core.domain.model

import com.gabcode.core.extension.empty

data class Item(
    val id: String,
    val title: String,
    val price: Int,
    val thumbnail: String,
    val pictures: List<Picture>
) {
    companion object {
        fun empty() = Item(String.empty(), String.empty(), 0, String.empty(), listOf())
    }
}

data class Picture(
    val id: String,
    val url: String
)


