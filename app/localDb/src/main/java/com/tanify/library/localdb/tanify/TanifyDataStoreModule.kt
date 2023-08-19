package com.tanify.library.localdb.tanify

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TanifyDataStoreModule {
    @Binds
    @Singleton
    abstract fun bindUserDataStore(impl: UserDataStoreImpl): UserDataStore
}