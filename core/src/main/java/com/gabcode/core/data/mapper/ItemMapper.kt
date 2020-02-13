package com.gabcode.core.data.mapper

import com.gabcode.core.domain.model.Picture
import com.gabcode.core.data.model.PictureDto
import com.gabcode.core.domain.model.Item
import com.gabcode.core.data.model.ItemDto


fun ItemDto.toDomainModel(): Item {
    return Item(
        this.id,
        this.title,
        this.price,
        this.currency,
        this.thumbnail,
        this.availableQuantity,
        this.condition,
        mapPictureListToDomain(this.pictures))
}

private fun mapPictureListToDomain(pictureDto: List<PictureDto>): List<Picture> {
    return when (pictureDto.isNullOrEmpty()) {
        true -> emptyList()
        else -> {
            pictureDto.map { Picture(it.id, it.url) }
        }
    }
}

fun List<ItemDto>.toDomainModel(): List<Item> {
    return this.map { it.toDomainModel() }
}
