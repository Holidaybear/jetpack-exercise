package tw.holidaybear.jetpack.exercise.ui

import androidx.compose.Composable
import androidx.ui.core.Opacity
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.layout.FlexColumn
import androidx.ui.layout.Padding
import androidx.ui.material.Divider
import androidx.ui.material.TopAppBar
import tw.holidaybear.jetpack.exercise.MainViewModel
import tw.holidaybear.jetpack.exercise.data.User

@Composable
fun HomeScreen(viewModel: MainViewModel) {
    FlexColumn {
        inflexible {
            TopAppBar(
                title = { Text(text = "GitHub Users") }
            )
        }
        flexible(flex = 1f) {
            VerticalScroller {
                Column {
                    UsersSection(viewModel.users.value)
                }
            }
        }
    }
}

@Composable
fun UsersSection(users: List<User>?) {
    users?.forEach { user ->
        UserCard(user)
        HomeScreenDivider()
    }
}

@Composable
fun HomeScreenDivider() {
    Padding(left = 14.dp, right = 14.dp) {
        Opacity(0.08f) {
            Divider()
        }
    }
}