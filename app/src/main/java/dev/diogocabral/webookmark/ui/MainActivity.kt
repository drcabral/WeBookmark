package dev.diogocabral.webookmark.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import dev.diogocabral.webookmark.R
import dev.diogocabral.webookmark.datasource.api.ApiEmptyResponse
import dev.diogocabral.webookmark.datasource.api.ApiErrorResponse
import dev.diogocabral.webookmark.datasource.api.ApiSuccessResponse
import dev.diogocabral.webookmark.model.localDataSourceModel.Book
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val bookViewModel: BookViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        observeAllBooksList()
        observeGetBooksByTitle()

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun observeAllBooksList() {
        bookViewModel.allBooks().observe(this, Observer { items ->
            if (items.isEmpty()) {
                bookViewModel.insert(
                    Book(
                        "Piquenique na estrada",
                        "Irmãos Strugatsky",
                        "",
                        320
                    )
                )
            }

            Log.d("TAG", "ITEMS: $items")
        })
    }

    private fun observeGetBooksByTitle() {
        bookViewModel.getBooksByTitle("Encarcerados").observe(this, Observer { response ->
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
