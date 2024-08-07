package salestaxcalculator.example.bcsalestax

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import salestaxcalculator.example.bcsalestax.ui.components.*
import salestaxcalculator.example.bcsalestax.ui.navigation.Screens
import salestaxcalculator.example.bcsalestax.ui.screens.BudgetScreen
import salestaxcalculator.example.bcsalestax.ui.screens.BudgetViewModel
import salestaxcalculator.example.bcsalestax.ui.screens.SalesTaxScreen
import salestaxcalculator.example.bcsalestax.ui.screens.SalesTaxViewModel
import salestaxcalculator.example.bcsalestax.ui.theme.AppTheme

@ExperimentalMaterial3Api
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
    // System bars
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT, Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT, Color.TRANSPARENT
            ),
        )
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                val navController = rememberNavController()
                val snackbarHostState = remember { SnackbarHostState() }
                val salesTaxViewModel = SalesTaxViewModel()
                val budgetViewModel = BudgetViewModel()

                Scaffold(
                    snackbarHost = { SnackbarHost(snackbarHostState) },
                    modifier = Modifier.fillMaxSize(),
                    topBar = { TopAppBar(title = "Sales Tax Calculator", salesTaxViewModel) },
                    bottomBar = { AppBottomNavigation(navController = navController) }
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Screens.Sales.navRoute,
                        enterTransition = {
                            slideIntoContainer(
                                AnimatedContentTransitionScope.SlideDirection.Left,
                                animationSpec = tween(600)
                            )
                        },
                        exitTransition = {
                            slideOutOfContainer(
                                AnimatedContentTransitionScope.SlideDirection.Right,
                                animationSpec = tween(600)
                            )
                        }
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
