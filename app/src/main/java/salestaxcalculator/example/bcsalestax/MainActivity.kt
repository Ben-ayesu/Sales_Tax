@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package salestaxcalculator.example.bcsalestax

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.BCSalesTaxTheme
import salestaxcalculator.example.bcsalestax.navigation.AppBottomNavigation
import salestaxcalculator.example.bcsalestax.navigation.Screens
import salestaxcalculator.example.bcsalestax.ui.Screens.BudgetScreen
import salestaxcalculator.example.bcsalestax.ui.Screens.BudgetViewModel
import salestaxcalculator.example.bcsalestax.ui.Screens.SalesTaxScreen
import salestaxcalculator.example.bcsalestax.ui.Screens.SalesTaxViewModel
import salestaxcalculator.example.bcsalestax.ui.components.*

@ExperimentalMaterial3Api
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BCSalesTaxTheme {
                val navController = rememberNavController()
                val salesTaxViewModel = SalesTaxViewModel()
                val budgetViewModel = BudgetViewModel()

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
                    NavHost(
                        navController = navController,
                        startDestination = Screens.Home.navRoute,
                    ) {
                        composable(Screens.Home.navRoute) { SalesTaxScreen(salesTaxViewModel) }
                        composable(Screens.Budget.navRoute) { BudgetScreen(budgetViewModel) }
                    }
                }
            }
        }
    }
}
