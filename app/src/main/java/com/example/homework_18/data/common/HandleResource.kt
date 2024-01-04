package com.example.homework_18.data.common

import android.util.Log
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class HandleResource {
    suspend fun <T: Any> safeApiCall(call: suspend () -> Response<T>) = flow {
        try {
            emit(Resource.Loader)
            val users = call()
            if (users.isSuccessful) {
                emit(Resource.Success(users.body()!!))
            } else {
                val error = users.errorBody()?.string()
                emit(Resource.Error(error))
            }
        } catch (e: Throwable) {
            Log.i("omiko", e.message.toString())
        } finally {
            emit(Resource.Idle)
        }
    }
}