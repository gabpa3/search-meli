package com.gabcode.core.data.model

import com.google.gson.annotations.SerializedName

data class ItemDto(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("price") val price: Double,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("pictures") val pictures: List<PictureDto>
)

data class PictureDto(
    @SerializedName("id") val id: String,
    @SerializedName("url") val url: String,
    @SerializedName("size") val size: String,
    @SerializedName("max_size") val maxSize: String
)