package com.midterm.securevpnproxy.data.di

import com.midterm.securevpnproxy.data.repository.AuthRepository
import com.midterm.securevpnproxy.domain.datasource.AuthDataSource
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
