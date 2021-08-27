package tw.holidaybear.jetpack.exercise.userdetail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.transform
import tw.holidaybear.jetpack.exercise.data.User
import tw.holidaybear.jetpack.exercise.data.UserRepository
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private val userLogin = MutableStateFlow("")

    val uiState: Flow<DetailUiState>
        get() = userLogin.transform { login ->
            emit(DetailUiState.StateLoading)
            try {
                val user = repository.getUser(login)
                emit(DetailUiState.StateLoaded(user))
            } catch (exception: Exception) {
                emit(DetailUiState.StateError("Error Occurred!"))
            }
        }

    fun getUserDetail(login: String) {
        userLogin.value = login
    }
}

sealed class DetailUiState {
    object StateLoading : DetailUiState()
    data class StateLoaded(val user: User) : DetailUiState()
    data class StateError(val message: String) : DetailUiState()
}
