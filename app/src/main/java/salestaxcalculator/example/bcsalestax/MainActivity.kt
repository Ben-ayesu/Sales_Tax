@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package salestaxcalculator.example.bcsalestax

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bcsalestax.R
import salestaxcalculator.salestax.bcsalestax.ui.theme.BCSalesTaxTheme
import java.text.NumberFormat
import androidx.compose.material3.Icon as Material3Icon
import androidx.compose.material3.Scaffold as Material3Scaffold

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BCSalesTaxTheme {
                Material3Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(text = stringResource(id = R.string.app_name)) },
                        )
                    }
                ) {
                    MainScreen()
                }
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

    //Calculations
    val amount = enterItemPrice.toDoubleOrNull() ?: 0.00
    val tax = enterTax.toDoubleOrNull() ?: 0.00

    val taxAmount = calculateTaxesTotal(amount, tax)
    val totalAmount= calculateTotalAmount(amount, taxAmount)

    Column(
        Modifier
            .padding(top = 60.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Calculate Title Text
        Text(
            stringResource(id = R.string.calculate),
            Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 8.dp),
            fontSize = 45.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        //Tax Text
        Text(
            stringResource(id = R.string.taxes),
            Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp),
            fontSize = 45.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        //Enter Item Price Text Field
        EditItemNumberField(
            enterItemPrice,
            onValueChange = { enterItemPrice = it }
        )
        //Enter Tax Rate Text Field
        EditTaxRate(
            value = enterTax,
            onValueChange = { enterTax = it })

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

@ExperimentalMaterial3Api
@Composable
fun EditItemNumberField(
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 8.dp),
        label = { Text("Enter the Item Price") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        maxLines = 1,
        leadingIcon = { Text(text = "$") },
        trailingIcon = {
            Material3Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Close",
                modifier = Modifier
                    .clickable { onValueChange("") }
            )
        },
        shape = CircleShape
    )
}

@ExperimentalMaterial3Api
@Composable
fun EditTaxRate(
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 8.dp),
        label = { Text(text = "Enter Tax Rate") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        maxLines = 1,
        leadingIcon = { Text(text = "%") },
        trailingIcon = {
            Material3Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Close",
                modifier = Modifier
                    .clickable { onValueChange("") }
            )
        },
        shape = CircleShape
    )
}

private fun calculateTaxesTotal(
    amount: Double,
    taxRate: Double
): Double {
    return taxRate / 100 * amount
}

private fun calculateTotalAmount(price: Double, taxAmount: Double): Double {
    return price + taxAmount
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BCSalesTaxTheme {
        MainScreen()
    }
}
