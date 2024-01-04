package com.example.homework_18.domain.repository

import com.example.homework_18.data.common.Resource
import com.example.homework_18.domain.response.UsersResponse
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    suspend fun getAllUser(): Flow<Resource<List<UsersResponse>>>
}