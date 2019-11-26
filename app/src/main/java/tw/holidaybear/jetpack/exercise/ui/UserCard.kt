package tw.holidaybear.jetpack.exercise.ui

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Alignment
import androidx.ui.core.Text
import androidx.ui.core.WithDensity
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.graphics.vector.DrawVector
import androidx.ui.layout.*
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.themeTextStyle
import androidx.ui.res.vectorResource
import tw.holidaybear.jetpack.exercise.R
import tw.holidaybear.jetpack.exercise.data.User

@Composable
fun UserAvatar() {
    val vector = +vectorResource(R.drawable.ic_person)

    WithDensity {
        Container(width = vector.defaultWidth.toDp(), height = vector.defaultHeight.toDp()) {
            DrawVector(vector)
        }
    }
}

@Composable
fun UserTitle(user: User) {
    Text(user.login, style = +themeTextStyle { subtitle2 })
}

@Composable
fun UserCard(user: User) {
    Ripple(bounded = true) {
        Clickable(onClick = {

        }) {
            Padding(left = 16.dp, right = 16.dp) {
                FlexRow {
                    inflexible {
                        Container(width = 60.dp, height = 60.dp) {
                            UserAvatar()
                        }
                    }
                    flexible(1f) {
                        Padding(left = 10.dp) {
                            Container(height = 60.dp, alignment = Alignment.CenterLeft) {
                                UserTitle(user)
                            }
                        }
                    }
                }
            }
        }
    }
}