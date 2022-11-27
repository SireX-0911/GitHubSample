package com.sirex.details.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sirex.common.scopes.Fragment
import com.sirex.common.scopes.ViewModelKey
import com.sirex.presentation.factory.ViewModelFactory
import com.sirex.presentation.viewmodel.UserDetailsViewModel
import com.sirex.presentation.viewmodel.UsersViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface UserDetailsViewModelModule {

    @Binds
    @Fragment
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(UserDetailsViewModel::class)
    fun bindViewModel(viewModel: UserDetailsViewModel): ViewModel

}
