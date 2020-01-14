package dev.diogocabral.webookmark.di

import androidx.room.Room
import dev.diogocabral.webookmark.repository.BookLocalRepository
import dev.diogocabral.webookmark.repository.BookRepository
import dev.diogocabral.webookmark.repository.database.BooksDatabase
import dev.diogocabral.webookmark.ui.BookViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val bookModules = module {
    viewModel { BookViewModel(get()) }

    single {
        Room.databaseBuilder(
            get(),
            BooksDatabase::class.java,
            "books_database"
        )
            .build()
    }

    single {
        get<BooksDatabase>().bookDAO()
    }

    single<BookRepository> { BookLocalRepository(get()) }
}