package tw.holidaybear.jetpack.exercise.ui

import androidx.annotation.DrawableRes
import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.animation.Crossfade
import androidx.ui.core.WithDensity
import androidx.ui.foundation.Clickable
import androidx.ui.graphics.vector.DrawVector
import androidx.ui.layout.Container
import androidx.ui.material.MaterialTheme
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.surface.Surface
import androidx.ui.res.vectorResource
import tw.holidaybear.jetpack.exercise.MainViewModel

@Composable
fun UsersApp(viewModel: MainViewModel) {
    MaterialTheme(colors = lightThemeColors) {
        Crossfade(AppStatus.currentScreen) { screen ->
            Surface {
                when (screen) {
                    is Screen.Home -> HomeScreen(viewModel)
                    is Screen.Detail -> DetailScreen(user = screen.user)
                }
            }
        }
    }
}

@Composable
fun VectorImageButton(@DrawableRes id: Int, onClick: () -> Unit) {
    Ripple(bounded = false) {
        Clickable(onClick = onClick) {
            VectorImage(id)
        }
    }
}

@Composable
fun VectorImage(@DrawableRes id: Int) {
    val vector = +vectorResource(id)

    WithDensity {
        Container(width = vector.defaultWidth.toDp(), height = vector.defaultHeight.toDp()) {
            DrawVector(vector)
        }
    }
}