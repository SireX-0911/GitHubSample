package com.sirex.domain.repository

import com.sirex.common.utils.network.NetworkStatus
import com.sirex.domain.entites.DbRepository
import com.sirex.domain.entites.DbUser
import com.sirex.domain.model.ApiUser
import kotlinx.coroutines.flow.Flow

interface UserReposRepository {

    suspend fun getReposForUser(userName: String): Flow<NetworkStatus<List<DbRepository>>>

}