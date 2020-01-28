package dev.diogocabral.webookmark.datasource

import androidx.lifecycle.LiveData
import dev.diogocabral.webookmark.datasource.api.ApiResponse
import dev.diogocabral.webookmark.model.localDataSourceModel.Book
import dev.diogocabral.webookmark.model.remoteDataSourceModel.GoogleBooksApiResponse

interface Repository {

    suspend fun insert(book: Book)

    fun getBooksByTitle(bookTitle: String): LiveData<ApiResponse<GoogleBooksApiResponse>>

    fun allBooks(): LiveData<List<Book>>
}
