package tw.holidaybear.jetpack.exercise.ui

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.core.sp
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.*
import androidx.ui.material.TopAppBar
import androidx.ui.res.imageResource
import androidx.ui.text.TextStyle
import tw.holidaybear.jetpack.exercise.R
import tw.holidaybear.jetpack.exercise.data.User
import tw.holidaybear.jetpack.exercise.util.image

@Composable
fun DetailScreen(user: User) {
    val image = +image(user.imageUrl) ?: +imageResource(R.drawable.placeholder)

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
                        DrawImage(image)
                    }
                    Text(text = user.login, style = TextStyle(fontSize = 24.sp))
                }
            }
        }
    }
}