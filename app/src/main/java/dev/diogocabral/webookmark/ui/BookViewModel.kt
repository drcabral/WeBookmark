package dev.diogocabral.webookmark.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.diogocabral.webookmark.model.Book
import dev.diogocabral.webookmark.repository.BookRepository
import kotlinx.coroutines.launch


class BookViewModel(private val repository: BookRepository): ViewModel() {

    lateinit var allBooks: LiveData<List<Book>>

    fun insert(book: Book) = viewModelScope.launch {
        repository.insert(book)
    }

    fun getAllBooks() {
        allBooks = repository.allBooks()
    }
}