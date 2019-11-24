package tw.holidaybear.jetpack.exercise.userlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tw.holidaybear.jetpack.exercise.data.API_TOKEN
import tw.holidaybear.jetpack.exercise.data.User
import tw.holidaybear.jetpack.exercise.data.GitHubAPI
import tw.holidaybear.jetpack.exercise.util.Event

class UserListViewModel(private val api: GitHubAPI) : ViewModel() {

    private val _items = MutableLiveData<List<User>>().apply { value = emptyList() }
    val items: LiveData<List<User>> = _items

    private val _isDataLoading = MutableLiveData<Boolean>()
    val isDataLoading: LiveData<Boolean> = _isDataLoading

    private val _openUserEvent = MutableLiveData<Event<String>>()
    val openUserEvent: LiveData<Event<String>> = _openUserEvent

    init {
        loadUsers()
    }

    fun loadUsers() {
        _isDataLoading.value = true
        viewModelScope.launch {
            val response = api.getUsers(API_TOKEN)
            withContext(Dispatchers.Main) {
                _items.value = response.await().body() ?: emptyList()
                _isDataLoading.value = false
            }
        }
    }

    fun openUser(login: String) {
        _openUserEvent.value = Event(login)
    }
}