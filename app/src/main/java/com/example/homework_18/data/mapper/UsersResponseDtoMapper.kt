package com.example.homework_18.data.mapper

import com.example.homework_18.data.dto.UsersResponseDto
import com.example.homework_18.domain.response.UsersResponse

fun UsersResponseDto.toDomain(): UsersResponse {
    return UsersResponse(
        avatar = avatar,
        email = email,
        firstName = firstName,
        id = id,
        lastName = lastName
    )
}