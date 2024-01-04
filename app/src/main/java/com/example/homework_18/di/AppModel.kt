package com.example.homework_18.di

import com.example.homework_18.data.service.UserService
import com.example.homework_18.data.service.UsersService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class UsersRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class UserRetrofit

@Module
@InstallIn(SingletonComponent::class)
object AppModel {

    private const val BASE_URL_USER = "https://reqres.in/api/users/"
    private const val BASE_URL_USERS = "https://run.mocky.io/v3/"

    @UsersRetrofit
    @Singleton
    @Provides
    fun provideUsersRetrofitClient(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL_USERS)
            .addConverterFactory(MoshiConverterFactory.create(
                Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            ))
            .build()

    @UserRetrofit
    @Singleton
    @Provides
    fun provideUserRetrofitClient(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL_USER)
            .addConverterFactory(MoshiConverterFactory.create(
                Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            ))
            .build()

    @Singleton
    @Provides
    fun provideUsersService(@UsersRetrofit retrofit: Retrofit): UsersService{
        return retrofit.create(UsersService::class.java)
    }

    @Singleton
    @Provides
    fun provideUserService(@UserRetrofit retrofit: Retrofit): UserService{
        return retrofit.create(UserService::class.java)
    }
}