package dev.diogocabral.webookmark.model.localDataSourceModel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Book(
    var title: String,
    var author: String,
    var imagePath: String?,
    var pages: Int,
    var pagesRead: Int,
    var percentPagesRead: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
