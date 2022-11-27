package com.sirex.users.di

import com.sirex.common.scopes.Fragment
import com.sirex.core.di.CoreComponent
import com.sirex.core.di.DaggerCoreComponent
import com.sirex.users.ui.UsersFragment
import dagger.Component

@Component(dependencies = [CoreComponent::class],
    modules = [UsersViewModelModule::class]
)
@Fragment
interface UsersComponent {
    fun inject(usersFragment: UsersFragment)

    @Component.Factory
    interface Factory {
        fun create(component: CoreComponent): UsersComponent
    }
}