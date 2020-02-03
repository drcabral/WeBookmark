package dev.diogocabral.webookmark.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import dev.diogocabral.webookmark.datasource.Repository
import dev.diogocabral.webookmark.datasource.api.ApiResponse
import dev.diogocabral.webookmark.model.remoteDataSourceModel.GoogleBooksApiResponse
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchViewModelTest {

    private lateinit var searchViewModel: SearchViewModel
    private lateinit var bookRepository: Repository

    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)

        bookRepository = mockk(relaxUnitFun = true)
        searchViewModel = SearchViewModel(bookRepository)
    }

    @Test
    fun `should get books by title from repository`() {
        val bookApiResponseLiveData = MutableLiveData<ApiResponse<GoogleBooksApiResponse>>()

        every { bookRepository.getBooksByTitle("Encarcerados") } returns bookApiResponseLiveData

        Assert.assertEquals(bookApiResponseLiveData, searchViewModel.getBooksByTitle("Encarcerados"))
    }
}
