package com.example.hw20.domain.usecase.user

import com.example.hw20.domain.model.UserDomainModel
import com.example.hw20.domain.repository.UserRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject


class DeleteUserUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(userModel: UserDomainModel) {
        val user = repository.findUser(userModel.id).firstOrNull()
        user?.let {
            repository.delete(it)
        }
    }
}