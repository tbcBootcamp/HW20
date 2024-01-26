package com.example.hw20.di

import com.example.hw20.domain.usecase.validation.EmptyInputUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Singleton
    @Provides
    fun provideInputValidatorUseCase(): EmptyInputUseCase {
        return EmptyInputUseCase()
    }
}