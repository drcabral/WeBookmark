package dev.diogocabral.webookmark.ui.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import dev.diogocabral.webookmark.R
import dev.diogocabral.webookmark.datasource.api.ApiEmptyResponse
import dev.diogocabral.webookmark.datasource.api.ApiErrorResponse
import dev.diogocabral.webookmark.datasource.api.ApiSuccessResponse
import dev.diogocabral.webookmark.ui.adapter.ResultBooksListAdapter
import dev.diogocabral.webookmark.ui.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.activity_search_book.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity() {

    private val searchViewModel: SearchViewModel by viewModel()
    private var resultBooksListAdapter: ResultBooksListAdapter = ResultBooksListAdapter(null, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_book)

        setupResultBooksListView()

        setUpBackButtonListener()

        books_title_input.setOnEditorActionListener { view, actionId, _ ->
            handleUserConfirmSearchInput(view, actionId)
        }
    }

    override fun onBackPressed() {
        val intent = Intent()
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    private fun setupResultBooksListView() {
        result_books_list.setHasFixedSize(true)
        result_books_list.adapter = resultBooksListAdapter
    }

    private fun setUpBackButtonListener() {
        img_button_back.setOnClickListener {
            val intent = Intent()
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    private fun observeGetBooksByTitle(bookTitle: String) {
        searchViewModel.getBooksByTitle(bookTitle).observe(this, Observer { response ->
            when (response) {
                is ApiErrorResponse -> showToastMessage("Sorry! We are facing internal problems by now. Wait a little and try again!")
                is ApiEmptyResponse -> showToastMessage("No books found for your search")
                is ApiSuccessResponse -> resultBooksListAdapter.updateData(response.body.books)
            }
        })
    }

    private fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun handleUserConfirmSearchInput(view: View, actionId: Int): Boolean {
        var handled = false
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            view.let { v ->
                val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                inputMethodManager?.hideSoftInputFromWindow(v.windowToken, 0)
            }

            handled = true

            val userInputBookTitle = books_title_input.text.toString()
            observeGetBooksByTitle(userInputBookTitle)
        }

        return handled
    }
}
