package dev.diogocabral.webookmark.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.diogocabral.webookmark.R
import dev.diogocabral.webookmark.model.localDataSourceModel.Book
import kotlinx.android.synthetic.main.cardview_book_item.view.book_title
import kotlinx.android.synthetic.main.cardview_book_item.view.book_authors
import kotlinx.android.synthetic.main.cardview_book_item.view.book_total_pages
import kotlinx.android.synthetic.main.cardview_book_item.view.book_pages_read

class UserBooksListAdapter(
    userBookList: List<Book>?,
    private val context: Context
) : RecyclerView.Adapter<UserBooksListAdapter.ViewHolder>() {

    private var data = userBookList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.cardview_book_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentBook = data?.get(position)
        currentBook?.let { holder.bindView(it) }
    }

    fun updateData(newData: List<Book>) {
        this.data = newData
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bindView(item: Book) {
            itemView.book_title.text = item.title
            itemView.book_authors.text = item.author
            itemView.book_total_pages.text = "${item.pages} pages"

            val percentageRead = item.pagesRead / item.pages * 100
            itemView.book_pages_read.text = "$percentageRead% read"

            // SETUP IMAGE WITH GLIDE
        }
    }
}
