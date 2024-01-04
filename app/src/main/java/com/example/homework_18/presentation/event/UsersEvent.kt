package com.example.homework_18.presentation.event

sealed class UsersEvent{
    data object GetAllUser: UsersEvent()
}