package dev.diogocabral.webookmark.di

import android.content.Context
import androidx.room.Room
import dev.diogocabral.webookmark.BuildConfig
import dev.diogocabral.webookmark.datasource.BookRepository
import dev.diogocabral.webookmark.datasource.Repository
import dev.diogocabral.webookmark.datasource.api.AuthInterceptor
import dev.diogocabral.webookmark.datasource.api.GoogleBooksApi
import dev.diogocabral.webookmark.datasource.api.GoogleServiceApiDataSource
import dev.diogocabral.webookmark.datasource.api.RemoteDatasource
import dev.diogocabral.webookmark.datasource.api.retrofitAdapter.LiveDataCallAdapterFactory
import dev.diogocabral.webookmark.datasource.room.BookDAO
import dev.diogocabral.webookmark.datasource.room.LocalDataSource
import dev.diogocabral.webookmark.datasource.room.RoomDataSource
import dev.diogocabral.webookmark.datasource.room.database.BooksDatabase
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
    single { provideLocalDataSource(get()) }

    factory { AuthInterceptor() }
    factory { provideOkHttpClient(get()) }
    factory { provideGoogleBooksApi(get()) }
    single { provideRemoteDataSource(get()) }
    single { provideRetrofit(get()) }

    single<Repository> { BookRepository(get(), get()) }
}

fun provideDatabase(context: Context): BooksDatabase {
    return Room.databaseBuilder(
        context,
        BooksDatabase::class.java,
        "books_database"
    )
        .build()
}

fun provideBookDao(booksDatabase: BooksDatabase): BookDAO {
    return booksDatabase.bookDAO()
}

fun provideLocalDataSource(bookDAO: BookDAO): LocalDataSource {
    return RoomDataSource(bookDAO)
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BuildConfig.GOOGLE_BOOKS_API_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(LiveDataCallAdapterFactory())
        .build()
}

fun provideRemoteDataSource(googleBooksApi: GoogleBooksApi): RemoteDatasource {
    return GoogleServiceApiDataSource(googleBooksApi)
}

fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
    return OkHttpClient().newBuilder().addInterceptor(authInterceptor).build()
}

fun provideGoogleBooksApi(retrofit: Retrofit): GoogleBooksApi {
    return retrofit.create(GoogleBooksApi::class.java)
}
