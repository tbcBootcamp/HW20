package com.example.hw20.domain.usecase.user

import com.example.hw20.domain.model.UserDomainModel
import com.example.hw20.domain.repository.UserRepository
import javax.inject.Inject

class InsertUserUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(user: UserDomainModel) {
        repository.insert(users = user)
    }
}