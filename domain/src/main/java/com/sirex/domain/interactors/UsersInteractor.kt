package com.sirex.domain.interactors

import com.sirex.common.utils.network.NetworkStatus
import com.sirex.domain.entites.DbUser
import com.sirex.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UsersInteractor @Inject constructor(
    private val usersRepository: UsersRepository
) {

   suspend fun getUsers(since: Int = 1) : Flow<NetworkStatus<List<DbUser>>> {
       return usersRepository.getAllUsers(since)
   }

    suspend fun getUserDetails(username: String) : Flow<NetworkStatus<DbUser>> {
        return usersRepository.getUserDetails(username)
    }
}