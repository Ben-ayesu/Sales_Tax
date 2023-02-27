package salestaxcalculator.example.bcsalestax

sealed class Screen(val route: String) {
    object Main: Screen(route = "main_screen")
    object Budget: Screen(route = "budget_screen")
}