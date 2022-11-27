package com.sirex.data.remote.datasource

import com.sirex.common.utils.network.NetworkStatus
import com.sirex.domain.model.ApiRepository
import com.sirex.domain.model.ApiUser

interface RepositoryRemoteDataSource {

    suspend fun getAllRepositoriesForUser(userName: String): NetworkStatus<List<ApiRepository>>

}