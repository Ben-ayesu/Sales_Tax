package salestaxcalculator.example.bcsalestax.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.bcsalestax.R

sealed class Screens(
    @StringRes val title: Int,
    val icon: ImageVector,
    val navRoute: String
) {
    object Home : Screens(R.string.home, Icons.Default.Home, NAV_HOME)
    object Budget : Screens(R.string.budget, Icons.Default.ShoppingCart, NAV_BUDGET)
}


const val NAV_HOME = "home"
const val NAV_BUDGET = "fav"

@Composable
fun AppBottomNavigation(
    navController: NavController
) {
    val screens = listOf(Screens.Home, Screens.Budget)

    NavigationBar {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        screens.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.title.toString()) },
                label = { Text(text = stringResource(id = item.title)) },
                selected = currentRoute == item.navRoute,
                onClick = {
                    navController.navigate(item.navRoute) {
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        }
    }
}

