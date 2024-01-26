package com.example.hw20.presentation.mapper

import com.example.hw20.domain.model.UserDomainModel
import com.example.hw20.presentation.model.UserUiModel



fun UserDomainModel.toPresentation() = UserUiModel(
    id = id,
    firstName = firstName,
    lastName = lastName,
    age = age,
    email = email
)

fun UserUiModel.toDomain() = UserDomainModel(
    id = id,
    firstName = firstName,
    lastName = lastName,
    age = age,
    email = email
)