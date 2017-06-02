package com.belatrix.authentication.business.injection.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {
    @Provides
    @Singleton
    fun providesApplicationContext(): Context {
        return application
    }
}