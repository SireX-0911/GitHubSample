package com.sirex.data.local.datasource

import com.sirex.data.local.dao.UserDao
import com.sirex.domain.entites.DbUser

internal class UserLocalDataSourceImpl(private val userDao: UserDao) : UserLocalDataSource {

    override suspend fun saveUsers(users: List<DbUser>) {
        userDao.insertUsers(users)
    }

    override suspend fun getAllUsers(): List<DbUser> {
        return userDao.queryUsers()
    }

    override suspend fun getUser(username: String): DbUser {
        return userDao.queryUser(username)
    }
}