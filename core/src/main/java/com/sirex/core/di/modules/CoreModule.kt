package com.sirex.core.di.modules

import android.app.Application
import com.sirex.core.App
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class CoreModule {

    @Binds
    @Singleton
    abstract fun bindApplication(application: App): Application
}