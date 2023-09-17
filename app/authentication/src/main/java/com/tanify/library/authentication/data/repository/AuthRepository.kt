import com.tanify.library.authentication.data.repository.network.AuthApiService
import com.tanify.library.authentication.data.repository.network.body.RegisterBody
import com.tanify.library.authentication.domain.datasource.AuthDataSource
import com.tanify.library.authentication.domain.model.login.LoginModel
import com.tanify.library.authentication.domain.model.register.RegisterModel
import com.tanify.library.authentication.domain.model.user_info.UserDataModel
import com.tanify.library.authentication.domain.payload.LoginPayload
import com.tanify.library.authentication.domain.payload.RegisterPayload
import com.tanify.library.authentication.domain.usecase.reset_password.ResetPasswordParam
import com.tanify.library.libcore.failure.Failure
import com.tanify.library.libcore.usecase.ResultModel
import com.tanify.library.networking.utils.ErrorDto
import com.tanify.library.networking.utils.safeCallApi
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthRepository @Inject constructor(
    private val authApiService: AuthApiService,
) : AuthDataSource {
    override fun loginWithEmailPass(payload: LoginPayload): Flow<ResultModel<LoginModel>> {
        TODO("Not yet implemented")
    }

    override fun registerWithEmailPassword(payload: RegisterPayload): Flow<ResultModel<RegisterModel>> {
        return flow {
            emit(
                safeCallApi(
                    apiCall = {
                        val body = RegisterBody(
                            email = payload.email,
                            name = payload.fullName,
                            password = payload.password
                        )
                        authApiService.register(body)
                    },
                    transformer = {
                        RegisterModel(
                            email = it.user?.email ?: "",
                            fullName = it.user?.name ?: "",
                            id = it.user?.id ?: ""
                        )
                    },
                    errorClass = ErrorDto::class,
                    errorMapper = {
                        Failure.FeatureError(error = it.message)
                    }
                ))
        }
    }

    override fun resetPassword(param: ResetPasswordParam): Flow<ResultModel<Boolean>> {
        TODO("Not yet implemented")
    }

    override fun getUserInfo(): Flow<ResultModel<UserDataModel>> {
        TODO("Not yet implemented")
    }

    override fun subscribeUserInfo() {
        TODO("Not yet implemented")
    }

}