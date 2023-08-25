package com.tanify.library.localdb.dataStore

import android.content.Context
import com.tanify.library.localdb.dataStore.annotation.SystemStorage
import com.tanify.library.localdb.dataStore.annotation.UserStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Provides
    @Singleton
    @UserStorage
    fun provideUserDatastore(
        @ApplicationContext context: Context,
    ): DataStore {
        return DataStoreImpl(context = context, dbName = USER_DB_NAME)
    }

    @Provides
    @Singleton
    @SystemStorage
    fun provideSystemDatastore(
        @ApplicationContext context: Context,
    ): DataStore {
        return DataStoreImpl(context = context, dbName = SYSTEM_DB_NAME)
    }

    private const val SYSTEM_DB_NAME = "system_db"
    private const val USER_DB_NAME = "user_db"
}