package com.belatrix.authentication.business.injection.module

import com.belatrix.authentication.BuildConfig
import com.belatrix.authentication.business.injection.scope.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class ApiClientModule(private val baseUrl: String) {
    @Provides
    @ApplicationScope
    fun providesInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
        return logging
    }

    @Provides
    @ApplicationScope
    fun providesOkHttpClient(logging: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
    }

    @Provides
    @ApplicationScope
    fun providesRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl(baseUrl)
                .client(client)
                .build()
    }
}