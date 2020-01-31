package dev.diogocabral.webookmark.model.remoteDataSourceModel

import com.google.gson.annotations.SerializedName

data class BookResponseInfo(
    @SerializedName("title") val title: String,
    @SerializedName("authors") val authors: List<String>,
    @SerializedName("imageLinks") val imageLinks: ImageLinks?,
    @SerializedName("pageCount") val pages: Int
)
