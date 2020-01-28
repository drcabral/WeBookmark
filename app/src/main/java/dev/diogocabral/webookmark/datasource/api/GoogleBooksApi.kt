package dev.diogocabral.webookmark.datasource.api

import androidx.lifecycle.LiveData
import dev.diogocabral.webookmark.model.remoteDataSourceModel.GoogleBooksApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleBooksApi {

    @GET("volumes")
    fun getBooksByTitle(@Query("q") titleParameter: String): LiveData<ApiResponse<GoogleBooksApiResponse>>
}
