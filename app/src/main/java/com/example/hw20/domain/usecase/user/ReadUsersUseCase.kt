package com.example.hw20.domain.usecase.user

import com.example.hw20.domain.repository.UserRepository
import javax.inject.Inject

class ReadUsersUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke() = repository.getAll()
}