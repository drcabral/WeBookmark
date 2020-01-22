package dev.diogocabral.webookmark.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import dev.diogocabral.webookmark.datasource.repository.BookDAO
import dev.diogocabral.webookmark.datasource.repository.BookLocalRepository
import dev.diogocabral.webookmark.model.Book
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BookLocalRepositoryTest {

    private lateinit var bookLocalRepository: BookLocalRepository
    private lateinit var bookDAO: BookDAO
    private lateinit var book: Book

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        bookDAO = mockk(relaxUnitFun = true)
        bookLocalRepository =
            BookLocalRepository(
                bookDAO
            )

        book = Book("A sample book", "Freddie Mercury", "/test.png", 300)
    }

    @Test
    fun `should call DAO object to insert in database`() {
        runBlocking { bookLocalRepository.insert(book) }

        coVerify { bookDAO.insert(book) }
    }

    @Test
    fun `should get list of books from database when calling DAO object`() {
        val bookLiveData = MutableLiveData<List<Book>>(listOf(book))

        every { bookDAO.getAll() } returns bookLiveData

        assertEquals(bookLiveData, bookLocalRepository.allBooks())


    }

}