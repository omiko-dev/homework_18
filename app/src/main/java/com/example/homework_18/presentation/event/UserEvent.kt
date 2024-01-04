package com.example.homework_18.presentation.event

sealed class UserEvent{
    data class GetUser(val id: Int): UserEvent()
}