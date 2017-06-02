package com.belatrix.authentication.business.injection.module

import com.belatrix.authentication.business.injection.scope.ApplicationScope
import com.belatrix.authentication.business.networking.AuthenticationApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ApisModule {
    @Provides
    @ApplicationScope
    fun providesAuthenticationApi(retrofit: Retrofit): AuthenticationApi {
        return retrofit.create(AuthenticationApi::class.java)
    }
}