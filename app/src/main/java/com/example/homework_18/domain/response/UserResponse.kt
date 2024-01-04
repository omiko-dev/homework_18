package com.example.homework_18.domain.response

import com.squareup.moshi.Json


data class UserResponse(
    val data: UserData
)

data class UserData(
    val avatar: String,
    val email: String,
    @Json(name = "first_name")
    val firstName: String,
    val id: Int,
    @Json(name = "last_name")
    val lastName: String
)