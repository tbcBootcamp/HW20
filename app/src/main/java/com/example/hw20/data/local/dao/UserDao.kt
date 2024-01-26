package com.example.hw20.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.hw20.data.local.model.UserEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {


    @Query("SELECT * FROM UserEntity")
    fun getAll(): Flow<List<UserEntity>>

    @Query("SELECT COUNT(*) FROM userentity")
    fun getUsersCount(): Flow<Int>

    @Query(
        "SELECT * FROM userentity WHERE first_name LIKE :first AND " +
                "last_name LIKE :last LIMIT 1"
    )
    fun findByName(first: String, last: String): Flow<UserEntity>

    @Query("SELECT * FROM UserEntity WHERE email LIKE :email LIMIT 1")
    fun findByEmail(email: String): Flow<UserEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM UserEntity WHERE id = :id LIMIT 1")
    fun findUser(id:Int): Flow<UserEntity>

    @Delete
    suspend fun deleteUser(user: UserEntity)

    @Update
    suspend fun updateUser(usersEntity: UserEntity)


}