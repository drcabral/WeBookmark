package dev.diogocabral.webookmark.ui.viewmodel

import androidx.lifecycle.ViewModel
import dev.diogocabral.webookmark.datasource.Repository

class SearchViewModel(private val repository: Repository) : ViewModel() {

    fun getBooksByTitle(title: String) = repository.getBooksByTitle(title)
}
