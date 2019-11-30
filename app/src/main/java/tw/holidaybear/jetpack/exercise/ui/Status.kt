package tw.holidaybear.jetpack.exercise.ui

import androidx.compose.Model
import tw.holidaybear.jetpack.exercise.data.User

sealed class Screen {
    object Home : Screen()
    data class Detail(val user: User) : Screen()
}

@Model
object AppStatus {
    var currentScreen: Screen = Screen.Home
}

fun navigateTo(destination: Screen) {
    AppStatus.currentScreen = destination
}
