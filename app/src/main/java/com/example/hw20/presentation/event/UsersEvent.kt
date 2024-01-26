package com.example.hw20.presentation.event

import com.example.hw20.presentation.model.UserUiModel

sealed class UsersEvent {
    data class CreateUser(val user: UserUiModel) : UsersEvent()
    data class UpdateUser(val user: UserUiModel) : UsersEvent()
}

sealed class SuccessEvent {
    data object SavedSuccessfully : SuccessEvent()
    data object EditedSuccessfully : SuccessEvent()
}