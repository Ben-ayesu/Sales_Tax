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
    val viewModel = MainViewModel()

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
                    viewModel.enterItemPrice.value
                ) { value ->
                    viewModel.enterItemPrice.value = value
                    viewModel.calculateAmounts()
                }
                //Enter Tax Rate Text Field
                EditTaxRate(
                    value = viewModel.enterTax.value
                ) { value ->
                    viewModel.enterTax.value = value
                    viewModel.calculateAmounts()

                }
                ResultsView(
                    taxAmount = viewModel.taxAmount.value,
                    totalAmount = viewModel.totalAmount.value
                )
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BCSalesTaxTheme {
        MainScreen()
    }
}
