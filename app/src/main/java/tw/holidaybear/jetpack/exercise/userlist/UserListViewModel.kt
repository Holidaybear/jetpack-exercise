package tw.holidaybear.jetpack.exercise.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import tw.holidaybear.jetpack.exercise.data.User
import tw.holidaybear.jetpack.exercise.data.UserRepository
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    fun getUsers(): Flow<PagingData<User>> = repository.getUsers().cachedIn(viewModelScope)
}
