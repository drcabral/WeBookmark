package dev.diogocabral.webookmark.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.diogocabral.webookmark.R
import dev.diogocabral.webookmark.model.localDataSourceModel.Book
import kotlinx.android.synthetic.main.cardview_book_item.view.*

class UserBooksListAdapter(
    userBookList: List<Book>?,
    private val context: Context
) : RecyclerView.Adapter<UserBooksListAdapter.ViewHolder>() {

    private var data = userBookList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.cardview_book_item, parent, false)
        return ViewHolder(view, context)
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

    class ViewHolder(itemView: View, val context: Context) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bindView(book: Book) {
            itemView.book_title.text = book.title
            itemView.book_authors.text = book.author
            itemView.book_total_pages.text = "${book.pages} pages"

            book.imagePath?.let {
                Glide.with(context)
                    .load(it)
                    .placeholder(R.drawable.ic_webookmark)
                    .dontAnimate()
                    .into(itemView.book_image)
            }

            val percentageRead = book.pagesRead / book.pages * 100
            itemView.book_pages_read.text = "$percentageRead% read"
        }
    }
}
