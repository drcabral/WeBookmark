package dev.diogocabral.webookmark.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import dev.diogocabral.webookmark.datasource.api.ApiResponse
import dev.diogocabral.webookmark.datasource.api.RemoteDatasource
import dev.diogocabral.webookmark.datasource.room.LocalDataSource
import dev.diogocabral.webookmark.model.localDataSourceModel.Book
import dev.diogocabral.webookmark.model.remoteDataSourceModel.GoogleBooksApiResponse
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BookRepositoryTest {

    private lateinit var bookLRepository: BookRepository
    private lateinit var localDataSource: LocalDataSource
    private lateinit var remoteDataSource: RemoteDatasource
    private lateinit var book: Book
    private lateinit var googleBooksApiResponse: GoogleBooksApiResponse

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        localDataSource = mockk(relaxed = true)
        remoteDataSource = mockk(relaxed = true)
        bookLRepository =
            BookRepository(
                localDataSource,
                remoteDataSource
            )

        book = Book(
            "A sample book",
            "Freddie Mercury",
            "/test.png",
            300
        )

        googleBooksApiResponse = mockk()
    }

    @Test
    fun `should call local data source object to insert in database`() {
        runBlocking { bookLRepository.insert(book) }

        coVerify { localDataSource.insert(book) }
    }

    @Test
    fun `should get all saved books from local data source`() {
        val bookLiveData = MutableLiveData<List<Book>>(listOf(book))

        every { localDataSource.getAll() } returns bookLiveData

        assertEquals(bookLiveData, bookLRepository.allBooks())
    }

    @Test
    fun `should call remote data source object to get books by title`() {
        val bookApiResponseLiveData = MutableLiveData<ApiResponse<GoogleBooksApiResponse>>()

        every { remoteDataSource.getBooksByTitle("Encarcerados") } returns bookApiResponseLiveData

        assertEquals(bookApiResponseLiveData, bookLRepository.getBooksByTitle("Encarcerados"))
    }
}
