package tw.holidaybear.jetpack.exercise.util

import androidx.fragment.app.Fragment
import tw.holidaybear.jetpack.exercise.UserApp

fun Fragment.getViewModelFactory(): ViewModelFactory {
    val repository = (requireContext().applicationContext as UserApp).userRepository
    return ViewModelFactory(repository)
}