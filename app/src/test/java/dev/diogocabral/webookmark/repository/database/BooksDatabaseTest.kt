package dev.diogocabral.webookmark.repository.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import dev.diogocabral.webookmark.model.Book
import dev.diogocabral.webookmark.testUtils.observeOnce
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.util.concurrent.Executors


@RunWith(RobolectricTestRunner::class)
class BooksDatabaseTest {

    private lateinit var database: BooksDatabase
    private lateinit var book: Book

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        database = Room
            .inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), BooksDatabase::class.java)
            .setTransactionExecutor(Executors.newSingleThreadExecutor())
            .build()

        book = Book("A sample book", "Freddie Mercury", "/test.png", 300)
    }

    @After
    fun cleanup() {
        database.close()
    }

    @Test
    fun `should insert book`() {
        runBlocking {
            database.bookDAO().insert(book)
        }

        val insertedBooksLiveData = database.bookDAO().getAll()

        insertedBooksLiveData.observeOnce {
            assertEquals(book, it[0])
        }
    }

    @Test
    fun `should get all books`() {
        runBlocking {
            database.bookDAO().insert(book)
            database.bookDAO().insert(book)
            database.bookDAO().insert(book)
        }

        val insertedBooksLivedata = database.bookDAO().getAll()

        insertedBooksLivedata.observeOnce {
            assertEquals(3, it.size)
        }
    }
}