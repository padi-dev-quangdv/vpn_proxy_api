package com.midterm.securevpnproxy.domain.di

import com.midterm.securevpnproxy.domain.usecase.check_login.CheckLoginUseCase
import com.midterm.securevpnproxy.domain.usecase.check_login.CheckLoginUseCaseImpl
import com.midterm.securevpnproxy.domain.usecase.getUserData.GetUserDataUseCase
import com.midterm.securevpnproxy.domain.usecase.getUserData.GetUserDataUseCaseImpl
import com.midterm.securevpnproxy.domain.usecase.login.LoginUseCase
import com.midterm.securevpnproxy.domain.usecase.login.LoginUseCaseImpl
import com.midterm.securevpnproxy.domain.usecase.register.RegisterUseCase
import com.midterm.securevpnproxy.domain.usecase.register.RegisterUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class DomainAppModule {

    @Binds
    @ViewModelScoped
    abstract fun bindRegisterUseCase(impl: RegisterUseCaseImpl): RegisterUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindLoginUseCase(impl: LoginUseCaseImpl): LoginUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindCheckLoginUseCase(impl: CheckLoginUseCaseImpl): CheckLoginUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetUserDataUseCase(impl: GetUserDataUseCaseImpl): GetUserDataUseCase

}