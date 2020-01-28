package dev.diogocabral.webookmark.datasource.api.retrofitAdapter

import androidx.lifecycle.LiveData
import dev.diogocabral.webookmark.datasource.api.ApiResponse
import dev.diogocabral.webookmark.datasource.api.UNKNOWN_CODE
import java.lang.reflect.Type
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response

class LiveDataCallAdapter<R>(private val responseType: Type) : CallAdapter<R, LiveData<ApiResponse<R>>> {
    override fun adapt(call: Call<R>): LiveData<ApiResponse<R>> {
        return object : LiveData<ApiResponse<R>>() {
            private var isSuccess = false
            override fun onActive() {
                super.onActive()
                if (!isSuccess) enqueue()
            }
            override fun onInactive() {
                super.onInactive()
                dequeue()
            }
            private fun dequeue() {
                if (call.isExecuted) call.cancel()
            }
            private fun enqueue() {
                call.enqueue(object : Callback<R> {
                    override fun onFailure(call: Call<R>, t: Throwable) {
                        postValue(
                            ApiResponse.create(
                                UNKNOWN_CODE,
                                t
                            )
                        )
                    }
                    override fun onResponse(call: Call<R>, response: Response<R>) {
                        postValue(
                            ApiResponse.create(
                                response
                            )
                        )
                        isSuccess = true
                    }
                })
            }
        }
    }
    override fun responseType(): Type = responseType
}
