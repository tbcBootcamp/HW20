package com.example.hw20.data.local.repository

import com.example.hw20.data.local.dao.UserDao
import com.example.hw20.data.local.mapper.toData
import com.example.hw20.data.local.mapper.toDomain
import com.example.hw20.domain.model.UserDomainModel
import com.example.hw20.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(
    private val userDao: UserDao
) : UserRepository {

    override fun getAll(): Flow<List<UserDomainModel>> {
        return userDao.getAll().map { users ->
            users.map {
                it.toDomain()
            }
        }
    }

    override fun getUsersCount(): Flow<Int> {
        return userDao.getUsersCount()
    }

    override fun findByName(first: String, last: String): Flow<UserDomainModel> {
        return userDao.findByName(first = first, last = last).map { users ->
            users.toDomain()
        }
    }

    override fun findByEmail(email: String): Flow<UserDomainModel> {
        return userDao.findByEmail(email = email).map { users ->
            users.toDomain()
        }
    }

    override fun findUser(id: Int): Flow<UserDomainModel> {
        return userDao.findUser(id).map {
            it.toDomain()
        }
    }

    override suspend fun insert(users: UserDomainModel) {
        userDao.insertUser(users.toData())
    }

    override suspend fun update(user: UserDomainModel) {
        userDao.updateUser(user.toData())
    }

    override suspend fun delete(user: UserDomainModel) {
        userDao.deleteUser(user.toData())
    }

}
