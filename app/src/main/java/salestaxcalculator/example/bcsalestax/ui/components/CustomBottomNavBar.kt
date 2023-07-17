package salestaxcalculator.example.bcsalestax.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import salestaxcalculator.example.bcsalestax.ui.navigation.Screens

@Composable
fun AppBottomNavigation(
    navController: NavController
) {
    val screens = listOf(Screens.Sales, Screens.Budget)

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
                }
            )
        }
    }
}