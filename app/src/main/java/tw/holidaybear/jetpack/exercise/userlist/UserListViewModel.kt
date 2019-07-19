package tw.holidaybear.jetpack.exercise.userlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tw.holidaybear.jetpack.exercise.data.User
import tw.holidaybear.jetpack.exercise.data.UserRemoteDataSource
import tw.holidaybear.jetpack.exercise.data.UserRepository
import tw.holidaybear.jetpack.exercise.util.Event

class UserListViewModel : ViewModel() {

    private val repository = UserRepository(UserRemoteDataSource)

    private val _items = MutableLiveData<List<User>>().apply { value = emptyList() }
    val items: LiveData<List<User>> = _items

    private val _openUserEvent = MutableLiveData<Event<String>>()
    val openUserEvent: LiveData<Event<String>> = _openUserEvent

    init {
        loadUsers()
    }

    fun loadUsers() {
        viewModelScope.launch {
            _items.value = repository.getUsers()
        }
    }

    fun openUser(login: String) {
        _openUserEvent.value = Event(login)
    }
}