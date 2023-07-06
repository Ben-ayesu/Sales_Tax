package salestaxcalculator.example.bcsalestax.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.bcsalestax.R

sealed class Screens(
    @StringRes val title: Int,
    val icon: ImageVector,
    val navRoute: String
) {
    object Sales : Screens(R.string.sale, Icons.Default.Home, NAV_SALES)
    object Budget : Screens(R.string.budget, Icons.Default.ShoppingCart, NAV_BUDGET)
}


const val NAV_SALES = "sales"
const val NAV_BUDGET = "budget"



