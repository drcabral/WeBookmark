package dev.diogocabral.webookmark.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.diogocabral.webookmark.model.Book
import dev.diogocabral.webookmark.repository.BookDAO

@Database(entities = [Book::class], version = 1)
abstract class BooksDatabase: RoomDatabase() {

    abstract fun bookDAO(): BookDAO
}