@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package salestaxcalculator.example.bcsalestax.ui.screens

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.BCSalesTaxTheme
import salestaxcalculator.example.bcsalestax.ui.components.EditItemNumberField
import salestaxcalculator.example.bcsalestax.ui.components.EditTaxRate
import salestaxcalculator.example.bcsalestax.ui.components.ResultsView

@ExperimentalMaterial3Api
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BCSalesTaxTheme {
                MainScreen()
            }
        }
    }
}

@ExperimentalMaterial3Api
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SuspiciousIndentation")
@Composable
fun MainScreen() {
    //Outlined Item price input
    var enterItemPrice by remember { mutableStateOf("") }
    //Outlined Tax Rate input
    var enterTax by remember { mutableStateOf("") }
    //Outlined Total you can afford
    var totalBudget by remember { mutableStateOf("") }

    //Calculations
    val amount = enterItemPrice.toDoubleOrNull() ?: 0.00
    val tax = enterTax.toDoubleOrNull() ?: 0.00

    val taxAmount = calculateTaxesTotal(amount, tax)
    val totalAmount = calculateTotalAmount(amount, taxAmount)

    Column(
        Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Scaffold(
            topBar = {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp),
                ) {
                    Text(
                        text = "Sales Tax Calculator",
                        fontSize = 36.sp
                    )
                }
            }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(top = 100.dp)
            ) {
                //Enter Item Price Text Field
                EditItemNumberField(
                    enterItemPrice,
                    onValueChange = { enterItemPrice = it }
                )
                //Enter Tax Rate Text Field
                EditTaxRate(
                    value = enterTax,
                    onValueChange = { enterTax = it }
                )
                ResultsView(
                    taxAmount = taxAmount,
                    totalAmount = totalAmount
                )
            }
        }
    }
}

/**
 * Calculates the total amount of taxes for a given amount and tax rate.
 *
 * @param amount a `Double` representing the amount to be taxed
 * @param taxRate a `Double` representing the tax rate as a percentage (e.g., a value of `20.0` represents a tax rate of 20%)
 * @return a `Double` representing the total amount of taxes calculated based on the given amount and tax rate
 */
private fun calculateTaxesTotal(
    amount: Double,
    taxRate: Double
): Double {
    return taxRate / 100 * amount
}

/**
 * Calculates the total amount including tax for a given amount and tax amount.
 *
 * @param amount a `Double` representing the amount to be taxed
 * @param taxAmount a `Double` representing the tax amount
 * @return a `Double` representing the total amount including tax calculated based on the given amount and tax amount
 */
private fun calculateTotalAmount(
    amount: Double,
    taxAmount: Double
): Double {
    return amount + taxAmount
}

/**
 * Calculates the item price based on the total amount including tax.
 *
 * @param amount a `Double` representing the total amount including tax
 * @param taxRate a `Double` representing the tax rate as a percentage (e.g., a value of `20.0` represents a tax rate of 20%)
 * @return a `Double` representing the item price calculated based on the given total amount and tax rate
 */
private fun calculateItemPriceBasedOnTotal(
    amount: Double,
    taxRate: Double
): Double {
    return amount / 1 + taxRate
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BCSalesTaxTheme {
        MainScreen()
    }
}
