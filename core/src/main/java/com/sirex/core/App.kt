package com.sirex.core

import android.app.Application
import android.content.Context
import com.sirex.core.di.CoreComponent
import com.sirex.core.di.DaggerCoreComponent

class App: Application() {

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(this)
    }

    companion object {
        @JvmStatic
        fun coreComponent(context: Context) = (context.applicationContext as App).coreComponent
    }

}