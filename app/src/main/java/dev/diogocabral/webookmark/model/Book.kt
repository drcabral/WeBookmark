package dev.diogocabral.webookmark.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Book(
    var title: String,
    var author: String,
    // TODO: add image to the book in room database
    var pages: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}