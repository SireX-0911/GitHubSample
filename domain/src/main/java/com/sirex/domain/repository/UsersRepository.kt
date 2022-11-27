package com.sirex.domain.repository

import com.sirex.common.utils.network.NetworkStatus
import com.sirex.domain.entites.DbUser
import com.sirex.domain.model.ApiUser
import kotlinx.coroutines.flow.Flow

interface UsersRepository {

    suspend fun getAllUsers(since: Int): Flow<NetworkStatus<List<DbUser>>>

    suspend fun getUserDetails(userName: String): Flow<NetworkStatus<DbUser>>

}