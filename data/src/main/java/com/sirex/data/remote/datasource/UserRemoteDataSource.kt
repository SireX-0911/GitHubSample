package com.sirex.data.remote.datasource

import com.sirex.common.utils.network.NetworkStatus
import com.sirex.domain.model.ApiUser

interface UserRemoteDataSource {

    suspend fun getAllUsers(since: Int): NetworkStatus<List<ApiUser>>

    suspend fun getUserDetails(userName: String): NetworkStatus<ApiUser>

}