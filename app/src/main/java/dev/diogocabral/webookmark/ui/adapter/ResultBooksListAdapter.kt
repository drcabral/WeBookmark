package dev.diogocabral.webookmark.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.diogocabral.webookmark.R
import dev.diogocabral.webookmark.model.remoteDataSourceModel.BookResponse
import dev.diogocabral.webookmark.ui.view.SearchActivity
import kotlinx.android.synthetic.main.cardview_book_item.view.*

class ResultBooksListAdapter(
    resultBookList: List<BookResponse>?,
    private val context: Context
) : RecyclerView.Adapter<ResultBooksListAdapter.ViewHolder>() {

    private var data = resultBookList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.cardview_book_item, parent, false)
        return ViewHolder(view, context)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentBookResponse = data?.get(position)
        currentBookResponse?.let { holder.bindView(it) }
    }

    fun updateData(newData: List<BookResponse>) {
        this.data = newData
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View, val context: Context) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bindView(item: BookResponse) {
            val bookInfo = item.bookInfo

            itemView.book_title.text = bookInfo.title
            itemView.book_authors.text =  TextUtils.join(",", bookInfo.authors)
            itemView.book_total_pages.text = "${bookInfo.pages} pages"

            itemView.book_pages_read.visibility = View.GONE

            bookInfo.imageLinks?.let {
                Glide.with(context)
                    .load(it.imagePath)
                    .placeholder(R.drawable.ic_webookmark)
                    .dontAnimate()
                    .into(itemView.book_image)
            }

            itemView.setOnClickListener {
                if(context is SearchActivity){
                    context.showSelectionDialogConfirmation(bookInfo)
                }
            }
        }
    }
}
