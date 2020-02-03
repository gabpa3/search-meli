package com.gabcode.core.data.mapper

import com.gabcode.core.domain.model.Picture
import com.gabcode.core.data.model.PictureDto
import com.gabcode.core.domain.model.Item
import com.gabcode.core.data.model.ItemDto


fun mapFromDataModel(itemDto: ItemDto): Item {
    return Item(
        itemDto.id,
        itemDto.title,
        itemDto.price,
        itemDto.thumbnail,
        mapPictureListToDomain(itemDto.pictures)
    )
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
    return this.map { mapFromDataModel(it) }
}
