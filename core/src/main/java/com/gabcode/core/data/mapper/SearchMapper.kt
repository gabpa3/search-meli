package com.gabcode.core.data.mapper

import com.gabcode.core.domain.model.SearchResult
import com.gabcode.core.data.model.ItemDto
import com.gabcode.core.data.model.PagingDto
import com.gabcode.core.data.model.SearchResultDto
import com.gabcode.core.domain.model.Paging
import com.gabcode.core.domain.model.Item


fun SearchResultDto<ItemDto>.toDomainModel(): SearchResult<Item> {
    val paging = this.pagingDto?.toDomainModel() ?: Paging.empty()
    val results: List<ItemDto> = this.results
    return SearchResult(paging = paging, results = results.toDomainModel())
}

private fun PagingDto.toDomainModel(): Paging {
    return Paging(this.total, this.offset, this.limit)
}




