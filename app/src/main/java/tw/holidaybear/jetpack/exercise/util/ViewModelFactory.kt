package tw.holidaybear.jetpack.exercise.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tw.holidaybear.jetpack.exercise.data.UserRepository
import tw.holidaybear.jetpack.exercise.userdetail.UserDetailViewModel
import tw.holidaybear.jetpack.exercise.userlist.UserListViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(
    private val userRepository: UserRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(UserListViewModel::class.java) ->
                    UserListViewModel(userRepository)
                isAssignableFrom(UserDetailViewModel::class.java) ->
                    UserDetailViewModel(userRepository)
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}