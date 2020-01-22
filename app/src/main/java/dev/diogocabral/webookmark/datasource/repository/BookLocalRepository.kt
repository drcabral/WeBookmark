package dev.diogocabral.webookmark.datasource.repository

import androidx.lifecycle.LiveData
import dev.diogocabral.webookmark.model.Book

class BookLocalRepository(private val bookDAO: BookDAO) :
    BookRepository {

    override suspend fun insert(book: Book) {
        bookDAO.insert(book)
    }

    override fun allBooks(): LiveData<List<Book>> {
        return bookDAO.getAll()
    }
}
