package dev.diogocabral.webookmark.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.diogocabral.webookmark.R

class SearchBookActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_book)
    }
}
