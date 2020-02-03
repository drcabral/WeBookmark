package dev.diogocabral.webookmark.model.remoteDataSourceModel

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ImageLinks(
    @SerializedName("thumbnail") val imagePath: String
) : Serializable
