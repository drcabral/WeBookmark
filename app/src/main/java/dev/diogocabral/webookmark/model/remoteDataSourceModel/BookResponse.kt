package dev.diogocabral.webookmark.model.remoteDataSourceModel

import com.google.gson.annotations.SerializedName

data class BookResponse(
    @SerializedName("volumeInfo") val bookInfo: BookResponseInfo
)
