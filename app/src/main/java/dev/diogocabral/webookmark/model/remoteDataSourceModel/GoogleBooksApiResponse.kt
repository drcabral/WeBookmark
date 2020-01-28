package dev.diogocabral.webookmark.model.remoteDataSourceModel

import com.google.gson.annotations.SerializedName

data class GoogleBooksApiResponse(
    @SerializedName("items") val books: List<BookResponse>
)
