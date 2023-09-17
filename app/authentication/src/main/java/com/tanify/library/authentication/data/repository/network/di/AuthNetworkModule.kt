package com.tanify.library.authentication.data.repository.network.di

import com.squareup.moshi.Moshi
import com.tanify.library.authentication.data.repository.network.AuthApiService
import com.tanify.library.networking.retrofit.RestfulNetworkModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AuthNetworkModule {

    @Provides
    @Singleton
    fun provideApiService(
        moshi: Moshi,
        okHttpClient: OkHttpClient,
    ): AuthApiService {
        val converterFactory = MoshiConverterFactory.create(moshi)

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(RestfulNetworkModule.BASE_URL)
            .addConverterFactory(converterFactory)
            .build()
            .create(AuthApiService::class.java)
    }
}

