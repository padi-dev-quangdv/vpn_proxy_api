package com.tanify.library.dns.domain.di

import com.tanify.library.dns.domain.usecase.server_list.ServerListUseCase
import com.tanify.library.dns.domain.usecase.server_list.ServerListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DnsDomainModule {

    @Binds
    @Singleton
    abstract fun bindServerList(impl: ServerListUseCaseImpl): ServerListUseCase

}