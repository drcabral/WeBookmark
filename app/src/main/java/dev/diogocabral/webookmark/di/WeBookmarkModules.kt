package dev.diogocabral.webookmark.di

import android.content.Context
import androidx.room.Room
import dev.diogocabral.webookmark.BuildConfig
import dev.diogocabral.webookmark.datasource.api.AuthInterceptor
import dev.diogocabral.webookmark.datasource.api.GoogleBooksApi
import dev.diogocabral.webookmark.datasource.repository.BookDAO
import dev.diogocabral.webookmark.datasource.repository.BookLocalRepository
import dev.diogocabral.webookmark.datasource.repository.BookRepository
import dev.diogocabral.webookmark.datasource.repository.database.BooksDatabase
import dev.diogocabral.webookmark.ui.BookViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val bookModules = module {
    viewModel { BookViewModel(get()) }

    single { provideDatabase(get()) }
    single { provideBookDao(get()) }
    single<BookRepository> { BookLocalRepository(get()) }

    factory { AuthInterceptor() }
    factory { provideOkHttpClient(get()) }
    factory { provideGoogleBooksApi(get()) }
    single { provideRetrofit(get()) }
}

fun provideDatabase(context: Context): BooksDatabase {
    return Room.databaseBuilder(
        context,
        BooksDatabase::class.java,
        "books_database"
    )
        .build()
}

fun provideBookDao(booksDatabase: BooksDatabase) : BookDAO {
    return booksDatabase.bookDAO()
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BuildConfig.GOOGLE_BOOKS_API_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
    return OkHttpClient().newBuilder().addInterceptor(authInterceptor).build()
}

fun provideGoogleBooksApi(retrofit: Retrofit): GoogleBooksApi {
    return retrofit.create(GoogleBooksApi::class.java)
}