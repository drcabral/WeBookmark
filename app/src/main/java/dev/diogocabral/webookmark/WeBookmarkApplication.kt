package dev.diogocabral.webookmark

import android.app.Application
import dev.diogocabral.webookmark.di.bookModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class WeBookmarkApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        stopKoin()

        startKoin {
            androidContext(this@WeBookmarkApplication)
            modules(bookModules)
        }
    }
}
