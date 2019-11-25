package tw.holidaybear.jetpack.exercise.ui

import androidx.compose.Composable
import androidx.compose.Model
import androidx.ui.core.Opacity
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.layout.FlexColumn
import androidx.ui.layout.Padding
import androidx.ui.material.Divider
import androidx.ui.material.MaterialTheme
import androidx.ui.material.TopAppBar
import androidx.ui.material.surface.Surface
import tw.holidaybear.jetpack.exercise.data.User

@Model
class UserStatus(
    var users: List<User> = emptyList()
)

@Composable
fun UsersApp(userStatus: UserStatus) {
    MaterialTheme(colors = lightThemeColors) {
        Surface {
            FlexColumn {
                inflexible {
                    TopAppBar(
                        title = { Text(text = "GitHub Users") }
                    )
                }
                flexible(flex = 1f) {
                    VerticalScroller {
                        Column {
                            HomeScreen(userStatus.users)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun HomeScreen(users: List<User>) {
    users.forEach { user ->
        UserCard(user)
        HomeScreenDivider()
    }
}

@Composable
private fun HomeScreenDivider() {
    Padding(left = 14.dp, right = 14.dp) {
        Opacity(0.08f) {
            Divider()
        }
    }
}