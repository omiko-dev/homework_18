package com.example.homework_18.domain.use_case

import com.example.homework_18.domain.repository.UserRepository
import javax.inject.Inject

class UserUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(id: Int) =
        userRepository.getUser(id)
}