package dev.diogocabral.webookmark.datasource.room

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import dev.diogocabral.webookmark.model.localDataSourceModel.Book
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RoomDataSourceTest {

    private lateinit var roomDataSource: RoomDataSource
    private lateinit var bookDao: BookDAO
    private lateinit var book: Book

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        bookDao = mockk(relaxUnitFun = true)
        roomDataSource =
            RoomDataSource(
                bookDao
            )

        book = Book(
            "A sample book",
            "Freddie Mercury",
            "/test.png",
            300,
            0,
            0
        )
    }

    @Test
    fun `should call DAO object to insert in database`() {
        runBlocking { roomDataSource.insert(book) }

        coVerify { bookDao.insert(book) }
    }

    @Test
    fun `should get list of books from database when calling DAO object`() {
        val bookLiveData = MutableLiveData<List<Book>>(listOf(book))

        every { bookDao.getAll() } returns bookLiveData

        Assert.assertEquals(bookLiveData, roomDataSource.getAll())
    }
}
