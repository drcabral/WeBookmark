package dev.diogocabral.webookmark.datasource.api

import retrofit2.http.GET

interface GoogleBooksApi {

    @GET("volumes?q=intitle")
    fun getBooksByTitle() {}

}