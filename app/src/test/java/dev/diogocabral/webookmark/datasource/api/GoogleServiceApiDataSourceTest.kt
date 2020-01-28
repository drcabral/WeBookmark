package dev.diogocabral.webookmark.datasource.api

import androidx.lifecycle.MutableLiveData
import dev.diogocabral.webookmark.model.remoteDataSourceModel.GoogleBooksApiResponse
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GoogleServiceApiDataSourceTest {

    private lateinit var googleServiceApiDataSource: GoogleServiceApiDataSource
    private lateinit var googleBooksApi: GoogleBooksApi
    private lateinit var googleBooksApiResponse: GoogleBooksApiResponse

    @Before
    fun setup() {
        googleBooksApi = mockk()
        googleBooksApiResponse = mockk()
        googleServiceApiDataSource = GoogleServiceApiDataSource(googleBooksApi)
    }

    @Test
    fun `should get books api success response when calling retrofit service`() {
        val apiSuccessResponse = ApiSuccessResponse(googleBooksApiResponse)
        val bookApiResponseLiveData = MutableLiveData<ApiResponse<GoogleBooksApiResponse>>(apiSuccessResponse)

        every { googleBooksApi.getBooksByTitle("intitle:Encarcerados") } returns bookApiResponseLiveData

        Assert.assertEquals(
            bookApiResponseLiveData,
            googleServiceApiDataSource.getBooksByTitle("Encarcerados")
        )
    }

    @Test
    fun `should get books api empty response when calling retrofit service`() {
        val apiEmptyResponse = ApiEmptyResponse<GoogleBooksApiResponse>()
        val bookApiResponseLiveData = MutableLiveData<ApiResponse<GoogleBooksApiResponse>>(apiEmptyResponse)

        every { googleBooksApi.getBooksByTitle("intitle:Encarcerados") } returns bookApiResponseLiveData

        Assert.assertEquals(
            bookApiResponseLiveData,
            googleServiceApiDataSource.getBooksByTitle("Encarcerados")
        )
    }

    @Test
    fun `should get books api error response when calling retrofit service`() {
        val apiErrorResponse = ApiErrorResponse<GoogleBooksApiResponse>(404, "not found")
        val bookApiResponseLiveData = MutableLiveData<ApiResponse<GoogleBooksApiResponse>>(apiErrorResponse)

        every { googleBooksApi.getBooksByTitle("intitle:Encarcerados") } returns bookApiResponseLiveData

        Assert.assertEquals(
            bookApiResponseLiveData,
            googleServiceApiDataSource.getBooksByTitle("Encarcerados")
        )
    }
}
