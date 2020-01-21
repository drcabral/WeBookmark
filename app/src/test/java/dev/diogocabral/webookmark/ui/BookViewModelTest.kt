package dev.diogocabral.webookmark.ui

import dev.diogocabral.webookmark.model.Book
import dev.diogocabral.webookmark.repository.BookRepository
import dev.diogocabral.webookmark.testUtils.observeOnce
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class BookViewModelTest {

    private lateinit var bookViewModel: BookViewModel
    private lateinit var bookRepository: BookRepository
    private lateinit var book: Book

    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)

        bookRepository = mockk()
        book = mockk()
        bookViewModel = BookViewModel(bookRepository)
    }

    @After
    fun cleanup() {
        Dispatchers.resetMain()
    }

    @Test
    fun `get all books from repository`() {
        every { bookViewModel.allBooks() } returns mockk()

        bookViewModel.allBooks()

        verify { bookRepository.allBooks() }
    }

    @Test
    fun `insert book with repository`() {
        every { bookViewModel.insert(book) } returns mockk()

        bookViewModel.insert(book)

        coVerify {
            bookRepository.insert(book)
        }
    }

}