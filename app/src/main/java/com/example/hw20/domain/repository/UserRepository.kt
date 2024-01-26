package com.example.hw20.domain.repository

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.hw20.data.local.model.UserEntity
import com.example.hw20.domain.model.UserDomainModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getAll(): Flow<List<UserDomainModel>>

    fun getUsersCount(): Flow<Int>

    fun findByName(first: String, last: String): Flow<UserDomainModel>

    fun findByEmail(email: String): Flow<UserDomainModel>

    suspend fun insert(users: UserDomainModel)

    suspend fun update(user: UserDomainModel)

    suspend fun delete(user: UserDomainModel)
    fun findUser(id: Int): Flow<UserDomainModel>
}









