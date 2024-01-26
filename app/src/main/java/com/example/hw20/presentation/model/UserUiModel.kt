package com.example.hw20.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserUiModel(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val email: String
): Parcelable

