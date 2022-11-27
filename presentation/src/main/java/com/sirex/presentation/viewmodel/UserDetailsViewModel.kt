package com.sirex.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sirex.common.utils.network.NetworkStatus
import com.sirex.domain.entites.DbRepository
import com.sirex.domain.entites.DbUser
import com.sirex.domain.interactors.UserReposInteractor
import com.sirex.domain.interactors.UsersInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class UserDetailsViewModel @Inject constructor(
    private val usersInteractor: UsersInteractor,
    private val userReposInteractor: UserReposInteractor
) : ViewModel() {

    private val _userLiveData = MutableLiveData<NetworkStatus<DbUser>>()
    val userLiveData = _userLiveData

    private val _reposLiveData = MutableLiveData<NetworkStatus<List<DbRepository>>>()
    val reposLiveData = _reposLiveData

    fun getUserDetails(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            usersInteractor.getUserDetails(username).collect {
                updateUserData(it)
            }
        }
    }

    fun getUserRepos(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userReposInteractor.getUserRepositories(username).collect {
                updateUserReposData(it)
            }
        }
    }

    private fun updateUserData(response: NetworkStatus<DbUser>) {
        when (response) {
            is NetworkStatus.Loading -> {
                if (response.data != null) {
                    _userLiveData.postValue(NetworkStatus.Loading(response.data))
                } else {
                    _userLiveData.postValue(NetworkStatus.Loading())
                }
            }
            is NetworkStatus.Success -> {
                if (response.data != null) {
                    _userLiveData.postValue(NetworkStatus.Success(response.data))
                }
            }
            is NetworkStatus.Error -> {
                if (response.data != null) {
                    _userLiveData.postValue(
                        NetworkStatus.Error(null)
                    )
                } else {
                    _userLiveData.postValue(
                        NetworkStatus.Error(
                            response.errorMessage,
                            null
                        )
                    )
                }
            }
        }
    }

    private fun updateUserReposData(response: NetworkStatus<List<DbRepository>>) {
        when (response) {
            is NetworkStatus.Loading -> {
                if (response.data.isNullOrEmpty().not()) {
                    _reposLiveData.postValue(NetworkStatus.Loading(response.data))
                } else {
                    _reposLiveData.postValue(NetworkStatus.Loading())
                }
            }
            is NetworkStatus.Success -> {
                if (response.data.isNullOrEmpty().not()) {
                    _reposLiveData.postValue(NetworkStatus.Success(response.data))
                }
            }
            is NetworkStatus.Error -> {
                if (response.data.isNullOrEmpty().not()) {
                    _reposLiveData.postValue(
                        NetworkStatus.Error(null)
                    )
                } else {
                    _reposLiveData.postValue(
                        NetworkStatus.Error(
                            response.errorMessage,
                            null
                        )
                    )
                }
            }
        }
    }
}