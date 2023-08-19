package com.tanify.library.authentication.data.repository.authmanager

import com.tanify.library.authentication.domain.datasource.AuthManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthManagerModule {

    @Binds
    @Singleton
    abstract fun bindAuthManager(authManagerImpl: AuthManagerImpl): AuthManager
}