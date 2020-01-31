package dev.diogocabral.webookmark.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.diogocabral.webookmark.datasource.Repository
import dev.diogocabral.webookmark.model.localDataSourceModel.Book
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository) : ViewModel() {

    fun allBooks() = repository.allBooks()

    fun insert(book: Book) {
        viewModelScope.launch {
            repository.insert(book)
        }
    }
}
