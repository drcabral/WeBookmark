package dev.diogocabral.webookmark.datasource.room

import androidx.lifecycle.LiveData
import dev.diogocabral.webookmark.model.localDataSourceModel.Book

class RoomDataSource(private val bookDAO: BookDAO) : LocalDataSource {
    override suspend fun insert(book: Book) {
        bookDAO.insert(book)
    }

    override fun getAll(): LiveData<List<Book>> {
        return bookDAO.getAll()
    }
}
