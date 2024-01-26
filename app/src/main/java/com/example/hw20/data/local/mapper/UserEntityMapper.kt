package com.example.hw20.data.local.mapper

import com.example.hw20.data.local.model.UserEntity
import com.example.hw20.domain.model.UserDomainModel


fun UserEntity.toDomain() = UserDomainModel(
    id = id,
    firstName = firstName.orEmpty(),
    lastName = lastName.orEmpty(),
    age = age,
    email = email.orEmpty()
)

fun UserDomainModel.toData(): UserEntity {
    return UserEntity(
        id = id,
        firstName = firstName,
        lastName = lastName,
        age = age,
        email = email
    )
}