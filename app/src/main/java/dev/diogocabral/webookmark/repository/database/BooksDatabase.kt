package dev.diogocabral.webookmark.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.diogocabral.webookmark.model.Book
import dev.diogocabral.webookmark.repository.BookDAO

@Database(entities = [Book::class], version = 1)
abstract class BooksDatabase: RoomDatabase() {

    abstract fun bookDAO(): BookDAO

    companion object {

        @Volatile
        private var INSTANCE: BooksDatabase? = null

        fun getInstance(context: Context): BooksDatabase {
            val tempInstance =
                INSTANCE

            if(tempInstance!= null) {
                return tempInstance
            }

            synchronized(BooksDatabase::class) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BooksDatabase::class.java,
                    "books_database"
                )
                    .build()

                INSTANCE = instance
                return  instance
            }
        }
    }
}