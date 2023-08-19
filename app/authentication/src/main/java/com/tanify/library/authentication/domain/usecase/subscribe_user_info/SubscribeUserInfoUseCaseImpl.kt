//package com.tanify.library.authentication.domain.usecase.subscribe_user_info
//
//import com.tanify.library.authentication.domain.datasource.AuthDataSource
//import com.tanify.library.libcore.usecase.ResultModel
//import javax.inject.Inject
//import kotlinx.coroutines.flow.Flow
//
//class SubscribeUserInfoUseCaseImpl @Inject constructor(
//    private val authDataSource: AuthDataSource,
//) : SubscribeUserInfoUseCase {
//    override fun execute(param: Nothing): Flow<ResultModel<Nothing>> {
//        authDataSource.subscribeUserInfo()
//    }
//}