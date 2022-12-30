package salestaxcalculator.salestax.bcsalestax.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat


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

    val view = LocalView.current
    if (view.context is Activity) {
        val window = (view.context as Activity).window
        window.statusBarColor = colors.primary.toArgb()
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = useDarkTheme
    } else {
        Snackbar() {
            Text(text = "Error: view context is not an Activity")
        }
    }


    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        shapes = Shapes,
        content = content
    )
}