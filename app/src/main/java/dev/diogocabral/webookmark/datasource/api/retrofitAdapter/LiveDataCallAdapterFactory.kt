package dev.diogocabral.webookmark.datasource.api.retrofitAdapter

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import retrofit2.CallAdapter
import retrofit2.Retrofit

class LiveDataCallAdapterFactory : CallAdapter.Factory() {
    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        val observableType =
            getParameterUpperBound(0, returnType as ParameterizedType) as? ParameterizedType
                ?: throw IllegalArgumentException("resource must be parameterized")
        return LiveDataCallAdapter<Any>(
            getParameterUpperBound(0, observableType)
        )
    }
}
