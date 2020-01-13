package dev.diogocabral.webookmark.repository

import androidx.lifecycle.LiveData
import dev.diogocabral.webookmark.model.Book

interface BookRepository {

    suspend fun insert(book: Book)

    fun allBooks() : LiveData<List<Book>>
}