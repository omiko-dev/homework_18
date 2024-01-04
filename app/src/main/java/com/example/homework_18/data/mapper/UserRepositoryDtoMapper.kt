package com.example.homework_18.data.mapper

import com.example.homework_18.data.dto.UserResponseDto
import com.example.homework_18.domain.response.UserData
import com.example.homework_18.domain.response.UserResponse

fun UserResponseDto.toDomain(): UserResponse {

    val userData = UserData(
        avatar = data.avatar,
        email = data.email,
        firstName = data.firstName,
        id = data.id,
        lastName = data.lastName
    )

    return UserResponse(
        data = userData
    )
}