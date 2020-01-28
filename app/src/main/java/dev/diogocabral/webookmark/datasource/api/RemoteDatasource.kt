package dev.diogocabral.webookmark.datasource.api

import androidx.lifecycle.LiveData
import dev.diogocabral.webookmark.model.remoteDataSourceModel.GoogleBooksApiResponse

interface RemoteDatasource {

    fun getBooksByTitle(title: String): LiveData<ApiResponse<GoogleBooksApiResponse>>
}
