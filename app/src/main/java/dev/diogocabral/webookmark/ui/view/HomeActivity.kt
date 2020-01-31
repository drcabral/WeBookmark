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
import dev.diogocabral.webookmark.ui.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by viewModel()
    private var userBooksListAdapter: UserBooksListAdapter = UserBooksListAdapter(null, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        setContentView(R.layout.activity_home)

        setupLocalBooksListView()

        observeAllBooksList()

        setupAddButtonListener()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ActivityRequestCodes.SEARCH_ACTIVITY_CODE.code) {
            if (resultCode == Activity.RESULT_OK) {
                val book = data?.getSerializableExtra("selectedBook") as Book?
                book?.let { homeViewModel.insert(it) }
            }
        }
    }

    private fun setupLocalBooksListView() {
        user_books_list.setHasFixedSize(true)
        user_books_list.adapter = userBooksListAdapter
    }

    private fun observeAllBooksList() {
        homeViewModel.allBooks().observe(this, Observer { items ->
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
            val intent = Intent(this, SearchActivity::class.java)
            startActivityForResult(intent, ActivityRequestCodes.SEARCH_ACTIVITY_CODE.code)
        }
    }
}
