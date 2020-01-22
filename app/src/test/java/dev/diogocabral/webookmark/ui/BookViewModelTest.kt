package dev.diogocabral.webookmark.ui

import androidx.lifecycle.MutableLiveData
import dev.diogocabral.webookmark.model.Book
import dev.diogocabral.webookmark.datasource.repository.BookRepository
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
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

        bookRepository = mockk(relaxUnitFun = true)
        bookViewModel = BookViewModel(bookRepository)

        book = Book("A sample book", "Freddie Mercury", "/test.png", 300)
    }

    @After
    fun cleanup() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should insert book with repository function`() {
        bookViewModel.insert(book)

        coVerify { bookRepository.insert(book) }
    }

    @Test
    fun `should get a list of all books from repository`() {
        val bookLiveData = MutableLiveData<List<Book>>(listOf(book))

        every { bookViewModel.allBooks() } returns bookLiveData

        assertEquals(mockk(), bookViewModel.allBooks())
    }
}