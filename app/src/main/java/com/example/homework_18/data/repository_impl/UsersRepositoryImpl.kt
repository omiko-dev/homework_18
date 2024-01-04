package com.example.homework_18.data.repository_impl

import com.example.homework_18.data.common.HandleResource
import com.example.homework_18.data.common.Resource
import com.example.homework_18.data.mapper.resourceMapper
import com.example.homework_18.data.mapper.toDomain
import com.example.homework_18.data.service.UsersService
import com.example.homework_18.domain.repository.UsersRepository
import com.example.homework_18.domain.response.UsersResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val usersService: UsersService,
    private val handleResource: HandleResource = HandleResource()
) :
    UsersRepository {
    override suspend fun getAllUser(): Flow<Resource<List<UsersResponse>>> {
        return handleResource.safeApiCall {
            usersService.getAllUser()
        }.map {
            it.resourceMapper { userList ->
                userList.map {
                        user -> user.toDomain()
                }
            }
        }
    }


}