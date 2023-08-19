package com.tanify.library.authentication.data.repository.network.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.converter.moshi.MoshiConverterFactory


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

//    @Provides
//    @Singleton
//    fun provideAppApiService(
//        factory: Converter.Factory,
//        okHttpClient: OkHttpClient,
//    ): AppApiService {
//        val retrofit = Retrofit.Builder()
//            .baseUrl(BuildConfig.BASE_API_URL)
//            .addConverterFactory(factory)
//            .client(okHttpClient)
//            .build()
//
//        return retrofit.create(AppApiService::class.java)
//    }

    @Provides
    @Singleton
    fun provideMoshiConverter(): Moshi {
        return Moshi.Builder().build()
    }

    @Provides
    @Singleton
    fun provideConverterFactory(moshi: Moshi): Converter.Factory {
        return MoshiConverterFactory.create(moshi).asLenient()
    }

//    @Provides
//    @Singleton
//    fun provideHttpClient(
//        loggingInterceptor: HttpLoggingInterceptor,
////        authInterceptor: AuthInterceptor,
//    ): OkHttpClient {
//        val clientBuilder = OkHttpClient.Builder()
//
//        if (BuildConfig.DEBUG) {
//            Timber.d("==> add logging interceptor")
//            clientBuilder.addInterceptor(loggingInterceptor)
//        }
//
//        val timeout = 30L
//
//        return clientBuilder
//            .connectTimeout(timeout, TimeUnit.SECONDS)
//            .writeTimeout(timeout, TimeUnit.SECONDS)
//            .readTimeout(timeout, TimeUnit.SECONDS)
////            .addInterceptor(authInterceptor)
//            .followRedirects(true)
//            .followSslRedirects(true)
//            .retryOnConnectionFailure(true)
//            .build()
//    }
}