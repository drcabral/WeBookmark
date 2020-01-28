package dev.diogocabral.webookmark.datasource.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.diogocabral.webookmark.datasource.room.BookDAO
import dev.diogocabral.webookmark.model.localDataSourceModel.Book

@Database(entities = [Book::class], version = 1)
abstract class BooksDatabase : RoomDatabase() {

    abstract fun bookDAO(): BookDAO
}
