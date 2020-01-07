package dev.diogocabral.webookmark.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import dev.diogocabral.webookmark.model.Book
import dev.diogocabral.webookmark.repository.BookRepository
import dev.diogocabral.webookmark.repository.database.BooksDatabase
import kotlinx.coroutines.launch


class BookViewModel(application: Application): AndroidViewModel(application) {

    private val repository: BookRepository
    val allBooks: LiveData<List<Book>>

    init {
        val bookDAO = BooksDatabase.getInstance(application).bookDAO()
        repository = BookRepository(bookDAO)
        allBooks = repository.allBooks
    }

    fun insert(book: Book) = viewModelScope.launch {
        repository.insert(book)
    }
}