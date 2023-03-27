package salestaxcalculator.example.bcsalestax

sealed class BottomBarScreen(val route: String) {
    object Main: BottomBarScreen(route = "main_screen")
    object Budget: BottomBarScreen(route = "budget_screen")
}