package com.sirex.data.local.datasource

import com.sirex.domain.entites.DbRepository

interface RepositoryLocalDataSource {

    suspend fun saveRepositories(repos: List<DbRepository>)

    suspend fun getAllRepositories(): List<DbRepository>

}