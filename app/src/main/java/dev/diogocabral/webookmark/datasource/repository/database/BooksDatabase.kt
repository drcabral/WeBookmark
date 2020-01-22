package dev.diogocabral.webookmark.datasource.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.diogocabral.webookmark.datasource.repository.BookDAO
import dev.diogocabral.webookmark.model.Book

@Database(entities = [Book::class], version = 1)
abstract class BooksDatabase : RoomDatabase() {

    abstract fun bookDAO(): BookDAO
}
