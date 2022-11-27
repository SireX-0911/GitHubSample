package com.sirex.core.di

import android.content.Context
import com.sirex.core.di.modules.CoreModule
import com.sirex.core.di.modules.DatabaseModule
import com.sirex.data.di.DataModule
import com.sirex.domain.repository.UserReposRepository
import com.sirex.domain.repository.UsersRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CoreModule::class, DatabaseModule::class, DataModule::class])
interface CoreComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): CoreComponent
    }

    val usersRepository: UsersRepository
    val userReposRepository: UserReposRepository
}