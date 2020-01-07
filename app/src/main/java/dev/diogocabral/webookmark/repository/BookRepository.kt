package dev.diogocabral.webookmark.repository

import androidx.lifecycle.LiveData
import dev.diogocabral.webookmark.model.Book

class BookRepository(private val bookDAO: BookDAO) {

    val allBooks: LiveData<List<Book>> = bookDAO.getAll()

    suspend fun insert(book: Book){
        bookDAO.insert(book)
    }
}