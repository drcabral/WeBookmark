package dev.diogocabral.webookmark.datasource.room

import androidx.lifecycle.LiveData
import dev.diogocabral.webookmark.model.localDataSourceModel.Book

interface LocalDataSource {

    suspend fun insert(book: Book)
    fun getAll(): LiveData<List<Book>>
}
