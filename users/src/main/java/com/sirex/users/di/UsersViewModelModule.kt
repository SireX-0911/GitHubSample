package com.sirex.users.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sirex.common.scopes.Fragment
import com.sirex.common.scopes.ViewModelKey
import com.sirex.presentation.factory.ViewModelFactory
import com.sirex.presentation.viewmodel.UsersViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface UsersViewModelModule {

    @Binds
    @Fragment
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(UsersViewModel::class)
    fun bindViewModel(viewModel: UsersViewModel): ViewModel

}
