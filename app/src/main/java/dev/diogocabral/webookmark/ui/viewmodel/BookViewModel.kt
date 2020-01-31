package dev.diogocabral.webookmark.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.diogocabral.webookmark.datasource.Repository
import dev.diogocabral.webookmark.model.localDataSourceModel.Book
import kotlinx.coroutines.launch

class BookViewModel(private val repository: Repository) : ViewModel() {

    fun allBooks() = repository.allBooks()

    fun getBooksByTitle(title: String) = repository.getBooksByTitle(title)

    fun insert(book: Book) {
        viewModelScope.launch {
            repository.insert(book)
        }
    }
}
