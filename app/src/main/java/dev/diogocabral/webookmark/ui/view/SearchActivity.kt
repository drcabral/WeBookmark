package dev.diogocabral.webookmark.ui.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import dev.diogocabral.webookmark.R
import dev.diogocabral.webookmark.datasource.api.ApiEmptyResponse
import dev.diogocabral.webookmark.datasource.api.ApiErrorResponse
import dev.diogocabral.webookmark.datasource.api.ApiSuccessResponse
import dev.diogocabral.webookmark.ui.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity() {

    private val searchViewModel: SearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_book)

        observeGetBooksByTitle()
    }

    private fun observeGetBooksByTitle() {
        searchViewModel.getBooksByTitle("Encarcerados").observe(this, Observer { response ->
            when (response) {
                is ApiErrorResponse -> showToastMessage(response.errorMessage)
                is ApiEmptyResponse -> showToastMessage("empty response")
                is ApiSuccessResponse -> showToastMessage(response.body.books[0].bookInfo.authors.toString())
            }
        })
    }

    private fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
