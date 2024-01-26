package com.example.hw20.presentation.fragments.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw20.domain.usecase.user.UsersUseCase
import com.example.hw20.presentation.event.SuccessEvent
import com.example.hw20.presentation.event.UsersEvent
import com.example.hw20.presentation.mapper.toDomain
import com.example.hw20.presentation.model.UserUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val usersUseCase: UsersUseCase
) : ViewModel() {


    private val _userState = MutableSharedFlow<SuccessEvent>()
    val userState: SharedFlow<SuccessEvent> = _userState.asSharedFlow()


    fun onEvent(event: UsersEvent) {
        when (event) {
            is UsersEvent.CreateUser -> {
                addUser(event.user)
            }

            is UsersEvent.UpdateUser -> {
                updateUser(event.user)
            }
        }
    }

    private fun updateUser(user: UserUiModel) {
        viewModelScope.launch {
            usersUseCase.updateUserUseCase(user)
        }.invokeOnCompletion {
            emitEvent(SuccessEvent.EditedSuccessfully)
        }
    }

    private fun addUser(userUiModel: UserUiModel) {
        viewModelScope.launch {
            usersUseCase.insertUserUseCase(userUiModel.toDomain())
        }.invokeOnCompletion {
            emitEvent(SuccessEvent.SavedSuccessfully)
        }
    }

    private fun emitEvent(event: SuccessEvent) {
        viewModelScope.launch { _userState.emit(event) }
    }

}