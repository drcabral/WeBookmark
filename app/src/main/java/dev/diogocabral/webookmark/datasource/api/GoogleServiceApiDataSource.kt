package dev.diogocabral.webookmark.datasource.api

import androidx.lifecycle.LiveData
import dev.diogocabral.webookmark.model.remoteDataSourceModel.GoogleBooksApiResponse

class GoogleServiceApiDataSource(private val bookService: GoogleBooksApi) : RemoteDatasource {

    override fun getBooksByTitle(title: String): LiveData<ApiResponse<GoogleBooksApiResponse>> {
        val titleParameter = "intitle:$title"
        return bookService.getBooksByTitle(titleParameter)
    }
}
