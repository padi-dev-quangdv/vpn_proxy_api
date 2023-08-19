package com.tanify.library.authentication.domain.di

import com.tanify.library.authentication.domain.usecase.auth_state.AuthStateUseCase
import com.tanify.library.authentication.domain.usecase.auth_state.AuthStateUseCaseImpl
import com.tanify.library.authentication.domain.usecase.get_user_info.GetUserInfoUseCase
import com.tanify.library.authentication.domain.usecase.get_user_info.GetUserInfoUseCaseImpl
import com.tanify.library.authentication.domain.usecase.login.LoginUseCase
import com.tanify.library.authentication.domain.usecase.login.LoginUseCaseImpl
import com.tanify.library.authentication.domain.usecase.register.RegisterUseCase
import com.tanify.library.authentication.domain.usecase.register.RegisterUseCaseImpl
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
    abstract fun bindGetUserInfoUseCase(impl: GetUserInfoUseCaseImpl): GetUserInfoUseCase

//    @Binds
//    @ViewModelScoped
//    abstract fun bindSubscribeUserInfoUseCase(impl: SubscribeUserInfoUseCaseImpl): SubscribeUserInfoUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindAuthStateUseCase(impl: AuthStateUseCaseImpl): AuthStateUseCase
}