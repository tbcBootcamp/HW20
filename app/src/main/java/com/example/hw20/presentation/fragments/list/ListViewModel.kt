package com.example.hw20.presentation.fragments.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw20.domain.usecase.user.UsersUseCase
import com.example.hw20.presentation.mapper.toDomain
import com.example.hw20.presentation.mapper.toPresentation
import com.example.hw20.presentation.model.UserUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ListViewModel @Inject constructor(
    private val usersUseCase: UsersUseCase
) : ViewModel() {


    suspend fun getAll(): Flow<List<UserUiModel>> {
        return usersUseCase.readUsersUseCase().map { it.map { it.toPresentation() } }
    }


    fun delete(userModel: UserUiModel) {
        viewModelScope.launch {
            usersUseCase.deleteUserUseCase(userModel.toDomain())
        }
    }


}
