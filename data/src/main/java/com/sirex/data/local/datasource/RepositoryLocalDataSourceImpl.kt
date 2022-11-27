package com.sirex.data.local.datasource

import com.sirex.data.local.dao.RepositoryDao
import com.sirex.domain.entites.DbRepository

internal class RepositoryLocalDataSourceImpl(private val repositoryDao: RepositoryDao) : RepositoryLocalDataSource {

    override suspend fun saveRepositories(repos: List<DbRepository>) {
        repositoryDao.insertRepositories(repos)
    }

    override suspend fun getAllRepositories(): List<DbRepository> {
        return repositoryDao.queryRepositories()
    }
}