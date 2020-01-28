package dev.diogocabral.webookmark.model.remoteDataSourceModel

import com.google.gson.annotations.SerializedName

data class ImageLinks(
    @SerializedName("thumbnail") val imagePath: String
)
