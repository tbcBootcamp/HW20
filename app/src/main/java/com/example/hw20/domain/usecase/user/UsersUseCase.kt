package com.example.hw20.domain.usecase.user

import com.example.hw20.domain.usecase.validation.EmptyInputUseCase
import javax.inject.Inject

data class UsersUseCase @Inject constructor(
    val deleteUserUseCase: DeleteUserUseCase,
    val getUsersCountUseCase: GetUsersCountUseCase,
    val insertUserUseCase: InsertUserUseCase,
    val readUsersUseCase: ReadUsersUseCase,
    val updateUserUseCase: UpdateUserUseCase,
    val inputUseCase: EmptyInputUseCase
)