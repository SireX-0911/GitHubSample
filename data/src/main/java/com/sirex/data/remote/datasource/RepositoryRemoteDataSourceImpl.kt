package com.sirex.data.remote.datasource

import com.sirex.common.utils.network.NetworkStatus
import com.sirex.data.remote.api.RepositoryApiService
import com.sirex.data.remote.api.UsersApiService
import com.sirex.data.utils.safeApiCall
import com.sirex.domain.model.ApiRepository
import com.sirex.domain.model.ApiUser

internal class RepositoryRemoteDataSourceImpl(private val repositoryApiService: RepositoryApiService) :
    RepositoryRemoteDataSource {

    override suspend fun getAllRepositoriesForUser(userName: String): NetworkStatus<List<ApiRepository>> {
        return safeApiCall { repositoryApiService.getUserRepos(userName) }
    }
}