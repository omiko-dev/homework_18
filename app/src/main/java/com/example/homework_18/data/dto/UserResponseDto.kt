package com.example.homework_18.data.dto

import com.squareup.moshi.Json

data class UserResponseDto(
    val data: UserDtoData
)

data class UserDtoData(
    val avatar: String,
    val email: String,
    @Json(name = "first_name")
    val firstName: String,
    val id: Int,
    @Json(name = "last_name")
    val lastName: String
)