package tw.holidaybear.jetpack.exercise.util

import androidx.fragment.app.Fragment
import tw.holidaybear.jetpack.exercise.data.GitHubAPI

fun Fragment.getViewModelFactory(): ViewModelFactory {
    val api = GitHubAPI.create()
    return ViewModelFactory(api)
}