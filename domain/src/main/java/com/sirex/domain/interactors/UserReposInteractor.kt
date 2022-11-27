package com.sirex.domain.interactors

import com.sirex.common.utils.network.NetworkStatus
import com.sirex.domain.entites.DbRepository
import com.sirex.domain.repository.UserReposRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserReposInteractor @Inject constructor(
    private val userReposRepository: UserReposRepository
) {
    suspend fun getUserRepositories(username: String): Flow<NetworkStatus<List<DbRepository>>> {
        return userReposRepository.getReposForUser(username)
    }
}