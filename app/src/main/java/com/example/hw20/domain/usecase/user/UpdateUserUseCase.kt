package com.example.hw20.domain.usecase.user

import com.example.hw20.domain.repository.UserRepository
import com.example.hw20.presentation.mapper.toDomain
import com.example.hw20.presentation.model.UserUiModel
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(user: UserUiModel) = repository.update(user = user.toDomain())
}