package dev.diogocabral.webookmark.ui.viewmodel

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import dev.diogocabral.webookmark.datasource.Repository
import dev.diogocabral.webookmark.model.localDataSourceModel.Book
import dev.diogocabral.webookmark.model.remoteDataSourceModel.BookResponseInfo
import dev.diogocabral.webookmark.ui.utils.BookMapper
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
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

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var bookRepository: Repository
    private lateinit var book: Book
    private lateinit var bookResponseInfo: BookResponseInfo

    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)

        bookRepository = mockk(relaxUnitFun = true)
        homeViewModel = HomeViewModel(bookRepository)

        book = Book(
            "A sample book",
            "Freddie Mercury",
            "/test.png",
            300,
            0,
            0
        )

        bookResponseInfo = BookResponseInfo(
            "A sample Book",
            listOf("Freddie Mercury"),
            null,
            300
        )
    }

    @After
    fun cleanup() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should insert book with repository function`() {
        mockkStatic(TextUtils::class)
        every { TextUtils.join(",", bookResponseInfo.authors) } returns "Freddie Mercury"

        val mappedBook = BookMapper.mapBookResponseInfoToBook(bookResponseInfo)

        homeViewModel.insert(bookResponseInfo)

        coVerify { bookRepository.insert(mappedBook) }
    }

    @Test
    fun `should get a list of all books from repository`() {
        val bookLiveData = MutableLiveData<List<Book>>(listOf(book))

        every { bookRepository.allBooks() } returns bookLiveData

        assertEquals(bookLiveData, homeViewModel.allBooks())
    }
}
