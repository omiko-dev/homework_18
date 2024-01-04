package com.example.homework_18.domain.use_case

import com.example.homework_18.domain.repository.UsersRepository
import javax.inject.Inject

class UsersUseCase @Inject constructor(private val usersRepository: UsersRepository) {
    suspend operator fun invoke() =
        usersRepository.getAllUser()
}