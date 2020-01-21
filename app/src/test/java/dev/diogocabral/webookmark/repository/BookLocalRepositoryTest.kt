package dev.diogocabral.webookmark.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.diogocabral.webookmark.model.Book
import io.mockk.*
import kotlinx.coroutines.runBlocking
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
        bookDAO = mockk()
        book = mockk()
        bookLocalRepository = BookLocalRepository(bookDAO)
    }

    @Test
    fun callDaoObjectToInsertInDatabase() {
        coEvery { bookLocalRepository.insert(book) } returns Unit

        runBlocking { bookLocalRepository.insert(book) }

        coVerify { bookDAO.insert(book) }
    }

    @Test
    fun callDaoObjectToGetAllBooksInDatabase() {
        every { bookLocalRepository.allBooks() } returns mockk()

        bookLocalRepository.allBooks()

        verify { bookDAO.getAll() }
    }

}