package com.sirex.data.repository

import com.sirex.common.utils.network.NetworkStatus
import com.sirex.data.coroutines.DispatcherProvider
import com.sirex.data.local.datasource.RepositoryLocalDataSource
import com.sirex.data.mappers.map
import com.sirex.data.remote.datasource.RepositoryRemoteDataSource
import com.sirex.data.utils.networkBoundResource
import com.sirex.domain.entites.DbRepository
import com.sirex.domain.repository.UserReposRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserReposRepositoryImpl(
    private val dispatcherProvider: DispatcherProvider,
    private val remoteDataSource: RepositoryRemoteDataSource,
    private val localDataSource: RepositoryLocalDataSource
) : UserReposRepository {

    @ExperimentalCoroutinesApi
    override suspend fun getReposForUser(userName: String): Flow<NetworkStatus<List<DbRepository>>> {
        return networkBoundResource(
            query = { queryLocalRepos() },
            fetch = { remoteDataSource.getAllRepositoriesForUser(userName) },
            saveFetchResult = { response ->
                response.data?.let {
                    val repos = it.map { repo ->
                        repo.map()
                    }
                    localDataSource.saveRepositories(repos)
                }
            },
            clearData = {}
        )
    }

    private fun queryLocalRepos(): Flow<List<DbRepository>> = flow {
        emit(localDataSource.getAllRepositories())
    }
}