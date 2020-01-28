package dev.diogocabral.webookmark.datasource

import androidx.lifecycle.LiveData
import dev.diogocabral.webookmark.datasource.api.ApiResponse
import dev.diogocabral.webookmark.datasource.api.RemoteDatasource
import dev.diogocabral.webookmark.datasource.room.LocalDataSource
import dev.diogocabral.webookmark.model.localDataSourceModel.Book
import dev.diogocabral.webookmark.model.remoteDataSourceModel.GoogleBooksApiResponse

class BookRepository(private val localDataSource: LocalDataSource, private val remoteDatasource: RemoteDatasource) : Repository {

    override suspend fun insert(book: Book) {
        localDataSource.insert(book)
    }

    override fun getBooksByTitle(bookTitle: String): LiveData<ApiResponse<GoogleBooksApiResponse>> {
        return remoteDatasource.getBooksByTitle(bookTitle)
    }

    override fun allBooks(): LiveData<List<Book>> {
        return localDataSource.getAll()
    }
}
