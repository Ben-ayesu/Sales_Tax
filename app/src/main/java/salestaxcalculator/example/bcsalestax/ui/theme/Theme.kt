package salestaxcalculator.salestax.bcsalestax.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable


private val LightColors = lightColorScheme(
    primary = Purple80,
    onPrimary = PurpleGrey80,
)


private val DarkColors = darkColorScheme(
    primary = Purple40,
    onPrimary = PurpleGrey40,
)

@Composable
fun BCSalesTaxTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (!useDarkTheme) {
        LightColors
    } else {
        DarkColors
    }

    // Top status bar color
//    val systemuiController = rememberSystemUiController()
//    SideEffect {
//        systemuiController.setStatusBarColor(
//            color = Color.Black
//        )
//    }

//    val view = LocalView.current
//    if (view.context is Activity) {
//        val window = (view.context as Activity).window
//        window.statusBarColor = colors.primary.toArgb()
//        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = useDarkTheme
//    } else {
//        Snackbar() {
//            Text(text = "Error: view context is not an Activity")
//        }
//    }


    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        shapes = Shapes,
        content = content
    )
}