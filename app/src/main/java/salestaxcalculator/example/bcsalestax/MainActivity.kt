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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import salestaxcalculator.example.bcsalestax.ui.Screens.BudgetScreen
import salestaxcalculator.example.bcsalestax.ui.Screens.BudgetViewModel
import salestaxcalculator.example.bcsalestax.ui.Screens.SalesTaxScreen
import salestaxcalculator.example.bcsalestax.ui.Screens.SalesTaxViewModel
import salestaxcalculator.example.bcsalestax.ui.components.*
import salestaxcalculator.example.bcsalestax.ui.navigation.Screens
import salestaxcalculator.example.bcsalestax.ui.theme.AppTheme

@ExperimentalMaterial3Api
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                val navController = rememberNavController()
                val snackbarHostState = remember { SnackbarHostState() }
                val salesTaxViewModel = SalesTaxViewModel()
                val budgetViewModel = BudgetViewModel()
                val showModalBottomSheet = rememberSaveable { mutableStateOf(false) }

                Scaffold(
                    snackbarHost = { SnackbarHost(snackbarHostState) },
                    modifier = Modifier
                        .fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = salesTaxViewModel.title,
                            salesTaxViewModel
                        )
                    },
                    bottomBar = {
                        AppBottomNavigation(navController = navController)
                    }
                    ) {
                    NavHost(
                        navController = navController,
                        startDestination = salesTaxViewModel.currentScreen,
                    ) {
                        composable(Screens.Sales.navRoute) {
                            SalesTaxScreen(
                                salesTaxViewModel,
                                snackbarHostState,
                            )
                        }
                        composable(Screens.Budget.navRoute) { BudgetScreen(budgetViewModel) }
                    }
                }
            }
        }
    }
}
