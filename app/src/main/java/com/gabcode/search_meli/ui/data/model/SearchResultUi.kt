package com.gabcode.search_meli.ui.data.model

import com.gabcode.core.domain.model.Item

data class SearchResultUi(
    val total: Int,
    val offset: Int,
    val query: String,
    val items: MutableList<Item>
)