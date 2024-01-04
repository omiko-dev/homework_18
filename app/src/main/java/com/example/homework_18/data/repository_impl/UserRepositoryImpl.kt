package com.example.homework_18.data.repository_impl

import com.example.homework_18.data.common.HandleResource
import com.example.homework_18.data.common.Resource
import com.example.homework_18.data.mapper.resourceMapper
import com.example.homework_18.data.mapper.toDomain
import com.example.homework_18.data.service.UserService
import com.example.homework_18.domain.repository.UserRepository
import com.example.homework_18.domain.response.UserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService,
    private val handleResource: HandleResource = HandleResource()
) :
    UserRepository {
    override suspend fun getUser(id: Int): Flow<Resource<UserResponse>> {
        return handleResource.safeApiCall {
            userService.getUser(id)
        }.map {
            it.resourceMapper { user ->
                user.toDomain()
            }
        }
    }
}