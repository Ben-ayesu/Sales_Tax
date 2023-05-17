package salestaxcalculator.example.bcsalestax.navigation

import androidx.annotation.StringRes
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.bcsalestax.R

sealed class Screens(
    @StringRes val title: Int,
    val icon: Int,
    val navRoute: String
) {
    object Home : Screens(R.string.home, R.drawable.ic_launcher_background, NAV_HOME)
    object Budget : Screens(R.string.budget, R.drawable.ic_launcher_background, NAV_BUDGET)
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
                icon = { painterResource(id = item.icon) },
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

