package dev.diogocabral.webookmark.ui.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import dev.diogocabral.webookmark.R
import dev.diogocabral.webookmark.model.localDataSourceModel.Book
import dev.diogocabral.webookmark.ui.adapter.UserBooksListAdapter
import dev.diogocabral.webookmark.ui.utils.ActivityRequestCodes
import dev.diogocabral.webookmark.ui.viewmodel.BookViewModel
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val bookViewModel: BookViewModel by viewModel()
    private var userBooksListAdapter: UserBooksListAdapter = UserBooksListAdapter(null, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        setContentView(R.layout.activity_home)

        setupLocalBooksListView()

        observeAllBooksList()
//        observeGetBooksByTitle()
        setupAddButtonListener()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == ActivityRequestCodes.SEARCH_ACTIVITY_CODE.code){
            if(resultCode == Activity.RESULT_OK){
                val book = data?.getSerializableExtra("selectedBook") as Book?
                book?.let { bookViewModel.insert(it) }
            }
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

    private fun setupAddButtonListener() {
        add_book_button.setOnClickListener {
            val intent = Intent(this, SearchBookActivity::class.java)
            startActivityForResult(intent, ActivityRequestCodes.SEARCH_ACTIVITY_CODE.code)
        }
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
