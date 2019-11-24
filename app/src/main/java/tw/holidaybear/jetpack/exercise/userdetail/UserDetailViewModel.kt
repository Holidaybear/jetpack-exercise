package tw.holidaybear.jetpack.exercise.userdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tw.holidaybear.jetpack.exercise.data.API_TOKEN
import tw.holidaybear.jetpack.exercise.data.GitHubAPI
import tw.holidaybear.jetpack.exercise.data.User
import tw.holidaybear.jetpack.exercise.data.defaultUser

class UserDetailViewModel(private val api: GitHubAPI) : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    fun loadUser(login: String) {
        viewModelScope.launch {
            val response = api.getUser(API_TOKEN, login)
            withContext(Dispatchers.Main) {
                _user.value = response.await().body() ?: defaultUser
            }
        }
    }
}