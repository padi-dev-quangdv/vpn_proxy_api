package com.midterm.securevpnproxy.di

import android.app.Application
import com.midterm.securevpnproxy.data.data_source.LoginDataSource
import com.midterm.securevpnproxy.data.data_source.RegisterDataSource
import com.midterm.securevpnproxy.data.repository.LoginRepositoryImpl
import com.midterm.securevpnproxy.data.repository.RegisterRepositoryImpl
import com.midterm.securevpnproxy.domain.repository.LoginRepository
import com.midterm.securevpnproxy.domain.repository.RegisterRepository
import com.midterm.securevpnproxy.domain.use_case.LoginUseCase
import com.midterm.securevpnproxy.domain.use_case.RegisterUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRegisterRepository(dataSource: RegisterDataSource): RegisterRepository {
        return RegisterRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideLoginRepository(dataSource: LoginDataSource): LoginRepository {
        return LoginRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideRegisterCases(repository: RegisterRepository): RegisterUseCase {
        return RegisterUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideLoginCases(repository: LoginRepository): LoginUseCase {
        return LoginUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideRegisterDataSource() : RegisterDataSource {
        return RegisterDataSource()
    }

    @Provides
    @Singleton
    fun provideLoginDataSource() : LoginDataSource {
        return LoginDataSource()
    }


}