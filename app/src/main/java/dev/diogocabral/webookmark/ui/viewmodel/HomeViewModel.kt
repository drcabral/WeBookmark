package dev.diogocabral.webookmark.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.diogocabral.webookmark.datasource.Repository
import dev.diogocabral.webookmark.model.localDataSourceModel.Book
import dev.diogocabral.webookmark.model.remoteDataSourceModel.BookResponseInfo
import dev.diogocabral.webookmark.ui.utils.BookMapper
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository) : ViewModel() {

    fun allBooks() = repository.allBooks()

    fun insert(bookResponseInfo: BookResponseInfo) {

        val mappedBook = BookMapper.mapBookResponseInfoToBook(bookResponseInfo)

        viewModelScope.launch {
            repository.insert(mappedBook)
        }
    }
}
