package tw.holidaybear.jetpack.exercise

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

class MainViewModel : ViewModel() {

    private val api = GitHubAPI.create()

    private val _users = MutableLiveData<List<User>>().apply { value = emptyList() }
    val users: LiveData<List<User>> = _users

    init {
        viewModelScope.launch {
            val response = api.getUsers(API_TOKEN)
            withContext(Dispatchers.Main) {
                _users.value = response.await().body() ?: emptyList()
            }
        }
    }
}