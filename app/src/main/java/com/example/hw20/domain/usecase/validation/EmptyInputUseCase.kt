package com.example.hw20.domain.usecase.validation

class EmptyInputUseCase {
    operator fun invoke(firstName: String, lastName: String, age: String, email: String): Boolean {
        return firstName.isNotBlank() && lastName.isNotBlank() && age.isNotBlank() && email.isNotBlank()
    }
}