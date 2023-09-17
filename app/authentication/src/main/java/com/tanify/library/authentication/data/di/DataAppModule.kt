package com.tanify.library.authentication.data.di

import AuthRepository
import com.tanify.library.authentication.data.repository.network.firebase.FirebaseAuthRepository
import com.tanify.library.authentication.domain.datasource.AuthDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataAppModule {

    @Binds
    @ViewModelScoped
    abstract fun bindAuthDataSource(repository: AuthRepository): AuthDataSource
}