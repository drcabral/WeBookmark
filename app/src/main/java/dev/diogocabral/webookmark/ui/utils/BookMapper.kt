package dev.diogocabral.webookmark.ui.utils

import android.text.TextUtils
import dev.diogocabral.webookmark.model.localDataSourceModel.Book
import dev.diogocabral.webookmark.model.remoteDataSourceModel.BookResponseInfo

class BookMapper {
    companion object {
        fun mapBookResponseInfoToBook(bookResponseInfo: BookResponseInfo): Book {
            return Book(
                bookResponseInfo.title,
                TextUtils.join(",", bookResponseInfo.authors),
                bookResponseInfo.imageLinks?.imagePath,
                bookResponseInfo.pages,
                0,
                0
            )
        }
    }
}