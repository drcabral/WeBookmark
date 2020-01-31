package dev.diogocabral.webookmark.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import dev.diogocabral.webookmark.datasource.Repository
import dev.diogocabral.webookmark.datasource.api.ApiResponse
import dev.diogocabral.webookmark.model.localDataSourceModel.Book
import dev.diogocabral.webookmark.model.remoteDataSourceModel.GoogleBooksApiResponse
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

    private lateinit var bookViewModel: HomeViewModel
    private lateinit var bookRepository: Repository
    private lateinit var book: Book

    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)

        bookRepository = mockk(relaxUnitFun = true)
        bookViewModel = HomeViewModel(bookRepository)

        book = Book(
            "A sample book",
            "Freddie Mercury",
            "/test.png",
            300,
            0,
            0
        )
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
    fun `should get books by title from repository`() {
        val bookApiResponseLiveData = MutableLiveData<ApiResponse<GoogleBooksApiResponse>>()

        every { bookRepository.getBooksByTitle("Encarcerados") } returns bookApiResponseLiveData

        assertEquals(bookApiResponseLiveData, bookViewModel.getBooksByTitle("Encarcerados"))
    }

    @Test
    fun `should get a list of all books from repository`() {
        val bookLiveData = MutableLiveData<List<Book>>(listOf(book))

        every { bookRepository.allBooks() } returns bookLiveData

        assertEquals(bookLiveData, bookViewModel.allBooks())
    }
}
