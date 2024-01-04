package com.example.homework_18.domain.repository

import com.example.homework_18.data.common.Resource
import com.example.homework_18.domain.response.UserResponse
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUser(id: Int): Flow<Resource<UserResponse>>
}