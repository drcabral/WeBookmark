package dev.diogocabral.webookmark.ui.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import dev.diogocabral.webookmark.R
import dev.diogocabral.webookmark.model.localDataSourceModel.Book
import dev.diogocabral.webookmark.ui.adapter.UserBooksListAdapter
import dev.diogocabral.webookmark.ui.viewmodel.BookViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val bookViewModel: BookViewModel by viewModel()
    private var userBooksListAdapter: UserBooksListAdapter = UserBooksListAdapter(null, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupLocalBooksListView()

        observeAllBooksList()
//        observeGetBooksByTitle()

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun setupLocalBooksListView() {
        user_books_list.setHasFixedSize(true)
        user_books_list.adapter = userBooksListAdapter
    }

    private fun observeAllBooksList() {
        bookViewModel.allBooks().observe(this, Observer { items ->
            if (items.isNotEmpty()) {
                handleLocalBooksListViewVisibility(items)
            }
        })
    }

    private fun handleLocalBooksListViewVisibility(books: List<Book>) {
        helper_container.visibility = View.GONE
        user_books_list.visibility = View.VISIBLE
        userBooksListAdapter.updateData(books)
    }

//    private fun observeGetBooksByTitle() {
//        bookViewModel.getBooksByTitle("Encarcerados").observe(this, Observer { response ->
//            when (response) {
//                is ApiErrorResponse -> showToastMessage(response.errorMessage)
//                is ApiEmptyResponse -> showToastMessage("empty response")
//                is ApiSuccessResponse -> showToastMessage(response.body.books[0].bookInfo.authors.toString())
//            }
//        })
//    }
//
//    private fun showToastMessage(message: String) {
//        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
//    }
}
