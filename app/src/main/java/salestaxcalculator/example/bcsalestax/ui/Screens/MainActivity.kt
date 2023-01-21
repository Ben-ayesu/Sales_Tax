@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package salestaxcalculator.example.bcsalestax.ui.screens

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.BCSalesTaxTheme
import salestaxcalculator.example.bcsalestax.data.Province
import salestaxcalculator.example.bcsalestax.data.provinces
import salestaxcalculator.example.bcsalestax.ui.components.*

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

    val selectedOptionState = remember { mutableStateOf(viewModel.selectedOptions) }

    val onOptionSelected: (String) -> Unit = { selectedOption ->
        selectedOptionState.value = selectedOption
    }

    var selectedProvince by remember { mutableStateOf("") }

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
                        .padding(top = 24.dp),
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
                    .padding(top = 125.dp)
            ) {
                //Enter Item Price Text Field
                EditItemNumberField(
                    viewModel.enterItemPrice.value
                ) { value ->
                    viewModel.enterItemPrice.value = value
                    viewModel.calculateAmounts()
                }
                //Select Row
                SelectRow(
                    viewModel.radioOptions,
                    modifier = Modifier
                        .padding(top = 16.dp, bottom = 12.dp),
                    selectedOptionState.value,
                    onOptionSelected
                )
                when(selectedOptionState.value){
                    "Custom Tax" -> {
                        EditTaxRate(
                            value = viewModel.enterTax.value
                        ) { value ->
                            viewModel.enterTax.value = value
                            viewModel.calculateAmounts()
                        }
                        CustomTaxResultsView(
                            taxAmount = viewModel.taxAmount.value,
                            totalAmount = viewModel.totalAmount.value,
                            modifier = Modifier
                                .padding(top = 4.dp)
                        )
                    }
                    "Provincial Tax" -> {
                        SearchableExpandedDropDownMenu(
                            listOfItems = provinces,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            onDropDownItemSelected = {

                            },
                            placeholder = "Select Province",
                            openedIcon = Icons.Outlined.ArrowDropDown,
                            closedIcon = Icons.Outlined.KeyboardArrowUp,
                            dropdownItem = { province ->
                                Text(text = province.provinceName)
                            },
                        )
                        ProvincialTaxResultsView(
                            PST = viewModel.pstAmount.value,
                            GST = viewModel.gstAmount.value,
                            totalAmount = viewModel.provTotalAmount.value
                        )
                    }
                }
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
