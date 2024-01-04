package com.example.homework_18.di

import com.example.homework_18.data.repository_impl.UserRepositoryImpl
import com.example.homework_18.data.repository_impl.UsersRepositoryImpl
import com.example.homework_18.data.service.UserService
import com.example.homework_18.data.service.UsersService
import com.example.homework_18.domain.repository.UserRepository
import com.example.homework_18.domain.repository.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideUsersRepository(usersService: UsersService): UsersRepository {
        return UsersRepositoryImpl(usersService = usersService)
    }

    @Singleton
    @Provides
    fun provideUserRepository(userService: UserService): UserRepository {
        return UserRepositoryImpl(userService = userService)
    }
}