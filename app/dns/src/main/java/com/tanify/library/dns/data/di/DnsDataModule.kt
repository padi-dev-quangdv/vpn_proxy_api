package com.tanify.library.dns.data.di

import com.tanify.library.dns.data.repository.DnsRepository
import com.tanify.library.dns.domain.datasource.DnsDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DnsDataModule {

    @Binds
    @Singleton
    abstract fun provideDnsDataSource(impl: DnsRepository): DnsDataSource

}