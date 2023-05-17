package salestaxcalculator.example.bcsalestax.ui.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.BCSalesTaxTheme
import salestaxcalculator.example.bcsalestax.navigation.AppBottomNavigation
import salestaxcalculator.example.bcsalestax.navigation.Screens
import salestaxcalculator.example.bcsalestax.ui.components.CustomTopAppBar

@ExperimentalMaterial3Api
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SuspiciousIndentation")
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        Modifier
            .fillMaxSize(),
        topBar = {
            CustomTopAppBar()
        },
        bottomBar = {
            AppBottomNavigation(navController = navController)
        }
    ) {
        NavHost(navController = navController, startDestination = Screens.Home.navRoute) {
            composable(Screens.Home.navRoute) { SalesTaxScreen() }
            composable(Screens.Budget.navRoute) { BudgetScreen() }
        }
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BCSalesTaxTheme {
//        MainScreen()
    }
}