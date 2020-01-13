package dev.diogocabral.webookmark.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.diogocabral.webookmark.model.Book
import dev.diogocabral.webookmark.repository.BookRepository
import kotlinx.coroutines.launch


class BookViewModel(private val repository: BookRepository): ViewModel() {

    val allBooks: LiveData<List<Book>> = repository.allBooks()

    fun insert(book: Book) = viewModelScope.launch {
        repository.insert(book)
    }
}