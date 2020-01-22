package dev.diogocabral.webookmark.datasource.repository

import androidx.lifecycle.LiveData
import dev.diogocabral.webookmark.model.Book

interface BookRepository {

    suspend fun insert(book: Book)

    fun allBooks() : LiveData<List<Book>>
}