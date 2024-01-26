package com.example.hw20.domain.usecase.user

import com.example.hw20.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersCountUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(): Flow<Int> = repository.getUsersCount()
}
