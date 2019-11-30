package tw.holidaybear.jetpack.exercise.ui

import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.core.sp
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.*
import androidx.ui.material.TopAppBar
import androidx.ui.text.TextStyle
import tw.holidaybear.jetpack.exercise.R
import tw.holidaybear.jetpack.exercise.data.User

@Composable
fun DetailScreen(user: User) {
    FlexColumn {
        inflexible {
            TopAppBar(
                title = {
                    Text(text = "User Detail")
                },
                navigationIcon = {
                    VectorImageButton(R.drawable.ic_back) {
                        navigateTo(Screen.Home)
                    }
                }
            )
        }
        flexible(flex = 1f) {
            VerticalScroller {
                Column(crossAxisAlignment = CrossAxisAlignment.Center, crossAxisSize = LayoutSize.Expand) {
                    Container(width = 120.dp, height = 120.dp) {
                        VectorImage(R.drawable.ic_person)
                    }
                    Text(text = user.login, style = TextStyle(fontSize = 24.sp))
                }
            }
        }
    }
}