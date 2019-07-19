package tw.holidaybear.jetpack.exercise.userdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tw.holidaybear.jetpack.exercise.data.User
import tw.holidaybear.jetpack.exercise.data.UserRemoteDataSource
import tw.holidaybear.jetpack.exercise.data.UserRepository

class UserDetailViewModel : ViewModel() {

    private val repository = UserRepository(UserRemoteDataSource)

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    fun loadUser(login: String) {
        viewModelScope.launch {
            _user.value = repository.getUser(login)
        }
    }
}