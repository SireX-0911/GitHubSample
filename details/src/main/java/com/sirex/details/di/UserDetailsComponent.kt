package com.sirex.details.di

import com.sirex.common.scopes.Fragment
import com.sirex.core.di.CoreComponent
import com.sirex.details.ui.UserDetailsFragment
import dagger.Component

@Component(dependencies = [CoreComponent::class],
    modules = [UserDetailsViewModelModule::class]
)
@Fragment
interface UserDetailsComponent {
    fun inject(userDetailsFragment: UserDetailsFragment)

    @Component.Factory
    interface Factory {
        fun create(component: CoreComponent): UserDetailsComponent
    }
}