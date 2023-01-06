@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package salestaxcalculator.example.bcsalestax.ui.Screens

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
import java.text.NumberFormat

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
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

                //Show tax amount results
                Text(
                    text = "Tax Amount: ${NumberFormat.getCurrencyInstance().format(taxAmount)}",
                    Modifier
                        .padding(8.dp),
                    fontSize = 25.sp
                )

                //Show Total amount with Taxes
                Text(
                    text = "Total Amount: ${NumberFormat.getCurrencyInstance().format(totalAmount)}",
                    fontSize = 25.sp
                )
            }
        }
    }
}

private fun calculateTaxesTotal(
    amount: Double,
    taxRate: Double
): Double {
    return taxRate / 100 * amount
}

private fun calculateTotalAmount(
    amount: Double,
    taxAmount: Double
): Double {
    return amount + taxAmount
}

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
