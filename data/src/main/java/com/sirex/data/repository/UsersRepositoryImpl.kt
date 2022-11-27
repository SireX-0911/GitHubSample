package com.sirex.data.repository

import com.sirex.common.utils.network.NetworkStatus
import com.sirex.data.coroutines.DispatcherProvider
import com.sirex.data.local.datasource.UserLocalDataSource
import com.sirex.data.mappers.map
import com.sirex.data.remote.datasource.UserRemoteDataSource
import com.sirex.data.utils.networkBoundResource
import com.sirex.domain.entites.DbUser
import com.sirex.domain.repository.UsersRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UsersRepositoryImpl(
    private val dispatcherProvider: DispatcherProvider,
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: UserLocalDataSource
) : UsersRepository {

    @ExperimentalCoroutinesApi
    override suspend fun getAllUsers(since: Int): Flow<NetworkStatus<List<DbUser>>> {
        return networkBoundResource(
            query = { queryLocalUsers() },
            fetch = { remoteDataSource.getAllUsers(since) },
            saveFetchResult = { response ->
                response.data?.let {
                    val users = it.map { user ->
                        user.map()
                    }
                    localDataSource.saveUsers(users)
                }
            },
            clearData = {}
        )
    }

    @ExperimentalCoroutinesApi
    override suspend fun getUserDetails(userName: String): Flow<NetworkStatus<DbUser>>  = networkBoundResource(
        query = { queryLocalUser(userName) },
        fetch = { remoteDataSource.getUserDetails(userName) },
        saveFetchResult = { response ->
            response.data?.let {
                localDataSource.saveUsers(listOf(it.map()))
            }
        },
        clearData = {}
    )

    private fun queryLocalUsers(): Flow<List<DbUser>> = flow {
        emit(localDataSource.getAllUsers())
    }

    private fun queryLocalUser(userName: String): Flow<DbUser> = flow {
        emit(localDataSource.getUser(userName))
    }
}