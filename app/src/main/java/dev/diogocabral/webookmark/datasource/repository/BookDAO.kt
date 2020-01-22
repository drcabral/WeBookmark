package dev.diogocabral.webookmark.datasource.repository

import androidx.lifecycle.LiveData
import androidx.room.*
import dev.diogocabral.webookmark.model.Book

@Dao
interface BookDAO {

    @Insert
    suspend fun insert(book: Book)

    @Update
    suspend fun update(book: Book)

    @Delete
    suspend fun delete(book: Book)

    @Query(value = "select * from books")
    fun getAll() : LiveData<List<Book>>
}