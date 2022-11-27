package com.sirex.data.remote.datasource

import com.sirex.common.utils.network.NetworkStatus
import com.sirex.data.remote.api.UsersApiService
import com.sirex.data.utils.safeApiCall
import com.sirex.domain.model.ApiUser

class UserRemoteDataSourceImpl (private val usersApiService: UsersApiService): UserRemoteDataSource {
    override suspend fun getAllUsers(since: Int): NetworkStatus<List<ApiUser>> {
        return safeApiCall { usersApiService.getAllUsers(since) }
    }

    override suspend fun getUserDetails(userName: String): NetworkStatus<ApiUser> {
        return safeApiCall { usersApiService.getUserInfo(userName) }
    }
}