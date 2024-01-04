package com.example.homework_18.data.service

import com.example.homework_18.data.dto.UserResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("{id}")
    suspend fun getUser(@Path("id") id: Int): Response<UserResponseDto>
}