package com.example.hw20.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hw20.data.local.dao.UserDao
import com.example.hw20.data.local.model.UserEntity


@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}