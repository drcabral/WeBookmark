package dev.diogocabral.webookmark.di

import dev.diogocabral.webookmark.ui.BookViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val bookModules = module {
    viewModel { BookViewModel(get()) }
}