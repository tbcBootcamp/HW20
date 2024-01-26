package com.example.hw20.di

import com.example.hw20.data.local.dao.UserDao
import com.example.hw20.data.local.repository.UserRepositoryImp
import com.example.hw20.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideLocalDatabaseRepository(
        userDao: UserDao
    ): UserRepository {
        return UserRepositoryImp(userDao = userDao)
    }
}
