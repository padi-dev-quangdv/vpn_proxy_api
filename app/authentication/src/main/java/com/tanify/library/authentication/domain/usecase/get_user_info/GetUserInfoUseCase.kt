package com.tanify.library.authentication.domain.usecase.get_user_info

import com.tanify.library.authentication.domain.model.user_info.UserDataModel
import com.tanify.library.libcore.usecase.FlowResultUseCase

interface GetUserInfoUseCase : FlowResultUseCase<Any, UserDataModel>