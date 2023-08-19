package com.tanify.library.authentication.domain.usecase.reset_password

import com.tanify.library.authentication.domain.datasource.AuthDataSource
import com.tanify.library.libcore.usecase.ResultModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ResetPasswordUseCaseImpl @Inject constructor(
    private val dataSource: AuthDataSource
) : ResetPasswordUseCase {
    override fun execute(param: ResetPasswordParam): Flow<ResultModel<Boolean>> {
        return dataSource.resetPassword(param)
    }

}