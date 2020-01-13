package dev.diogocabral.webookmark.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.diogocabral.webookmark.model.Book
import dev.diogocabral.webookmark.repository.BookRepository
import dev.diogocabral.webookmark.repository.database.BooksDatabase
import kotlinx.coroutines.launch


class BookViewModel(context: Context): ViewModel() {

    private val repository: BookRepository
    val allBooks: LiveData<List<Book>>

    init {
        val bookDAO = BooksDatabase.getInstance(context).bookDAO()
        repository = BookRepository(bookDAO)
        allBooks = repository.allBooks
    }

    fun insert(book: Book) = viewModelScope.launch {
        repository.insert(book)
    }
}