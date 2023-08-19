package com.tanify.library.authentication.domain.usecase.get_user_info

import com.tanify.library.authentication.domain.datasource.AuthDataSource
import com.tanify.library.libcore.usecase.ResultModel
import com.tanify.library.authentication.domain.model.user_info.UserDataModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetUserInfoUseCaseImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
) : GetUserInfoUseCase {
    override fun execute(param: Any): Flow<ResultModel<UserDataModel>> = authDataSource.getUserInfo()
}