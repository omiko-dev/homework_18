package com.example.homework_18.data.mapper

import com.example.homework_18.data.common.Resource

fun <T, D> Resource<T>.resourceMapper(mapper: (T) -> D): Resource<D> {
    return when(this){
        is Resource.Success -> Resource.Success(mapper(success))
        is Resource.Error -> Resource.Error(error = error)
        is Resource.Loader -> Resource.Loader
        is Resource.Idle -> Resource.Idle
    }
}
