package dev.diogocabral.webookmark.datasource.api

import dev.diogocabral.webookmark.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url().newBuilder().addQueryParameter("key", BuildConfig.GOOGLE_BOOKS_API_KEY).build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}
