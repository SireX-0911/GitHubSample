package com.sirex.data.di

import com.sirex.data.coroutines.DefaultDispatcherProvider
import com.sirex.data.coroutines.DispatcherProvider
import com.sirex.data.local.dao.RepositoryDao
import com.sirex.data.local.dao.UserDao
import com.sirex.data.local.datasource.RepositoryLocalDataSource
import com.sirex.data.local.datasource.RepositoryLocalDataSourceImpl
import com.sirex.data.local.datasource.UserLocalDataSource
import com.sirex.data.local.datasource.UserLocalDataSourceImpl
import com.sirex.data.remote.api.RepositoryApiService
import com.sirex.data.remote.api.UsersApiService
import com.sirex.data.remote.datasource.RepositoryRemoteDataSource
import com.sirex.data.remote.datasource.RepositoryRemoteDataSourceImpl
import com.sirex.data.remote.datasource.UserRemoteDataSource
import com.sirex.data.remote.datasource.UserRemoteDataSourceImpl
import com.sirex.data.repository.UserReposRepositoryImpl
import com.sirex.data.repository.UsersRepositoryImpl
import com.sirex.domain.repository.UserReposRepository
import com.sirex.domain.repository.UsersRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class DataModule {

    @Provides
    @Singleton
    internal fun provideDispatcherProvider(): DispatcherProvider = DefaultDispatcherProvider()

    @Provides
    fun provideUserRemoteDataSource(usersApiService: UsersApiService): UserRemoteDataSource {
        return UserRemoteDataSourceImpl(usersApiService)
    }

    @Provides
    fun provideUserLocalDataSource(userDao: UserDao): UserLocalDataSource {
        return UserLocalDataSourceImpl(userDao)
    }

    @Provides
    fun provideRepositoryRemoteDataSource(repositoryApiService: RepositoryApiService): RepositoryRemoteDataSource {
        return RepositoryRemoteDataSourceImpl(repositoryApiService)
    }

    @Provides
    fun provideRepositoryLocalDataSource(repositoryDao: RepositoryDao): RepositoryLocalDataSource {
        return RepositoryLocalDataSourceImpl(repositoryDao)
    }

    @Singleton
    @Provides
    fun provideUsersRepository(
        dispatcherProvider: DispatcherProvider,
        userRemoteDataSource: UserRemoteDataSource,
        userLocalDataSource: UserLocalDataSource
    ): UsersRepository {
        return UsersRepositoryImpl(dispatcherProvider, userRemoteDataSource, userLocalDataSource)
    }

    @Singleton
    @Provides
    fun provideUserReposRepository(
        dispatcherProvider: DispatcherProvider,
        remoteDataSource: RepositoryRemoteDataSource,
        localDataSource: RepositoryLocalDataSource
    ): UserReposRepository {
        return UserReposRepositoryImpl(dispatcherProvider, remoteDataSource, localDataSource)
    }

}