package com.belatrix.authentication.business.injection.component

import android.content.Context
import com.belatrix.authentication.business.injection.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    /**
     * Exposes the Application Context to any component
     * which depends on this
     */
    fun providesApplicationContext(): Context
}