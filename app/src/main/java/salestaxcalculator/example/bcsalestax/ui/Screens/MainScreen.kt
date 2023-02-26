package salestaxcalculator.example.bcsalestax.ui.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.BCSalesTaxTheme
import salestaxcalculator.example.bcsalestax.data.provinces
import salestaxcalculator.example.bcsalestax.ui.components.BudgetResultsScreen
import salestaxcalculator.example.bcsalestax.ui.components.CustomTaxResultsView
import salestaxcalculator.example.bcsalestax.ui.components.EditBudgetRate
import salestaxcalculator.example.bcsalestax.ui.components.EditItemNumberField
import salestaxcalculator.example.bcsalestax.ui.components.EditTaxRate
import salestaxcalculator.example.bcsalestax.ui.components.ProvincialTaxResultsView
import salestaxcalculator.example.bcsalestax.ui.components.SearchableExpandedDropDownMenu
import salestaxcalculator.example.bcsalestax.ui.components.SelectRow
import salestaxcalculator.example.bcsalestax.ui.screens.MainViewModel

@ExperimentalMaterial3Api
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SuspiciousIndentation")
@Composable
fun MainScreen() {
    val viewModel = MainViewModel()

    val selectedOptionState = remember { mutableStateOf(viewModel.selectedOptions) }

    val onOptionSelected: (String) -> Unit = { selectedOption ->
        selectedOptionState.value = selectedOption
    }

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
            },
            bottomBar = {
                Row {

                }
            }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(top = 125.dp)
            ) {
                //Enter Item Price Text Field
                // TODO need to add calculate provincial state on type
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
                        .padding(top = 16.dp, bottom = 12.dp)
                        .align(Alignment.Start),
                    selectedOptionState.value,
                    onOptionSelected
                )
                when (selectedOptionState.value) {
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
                            onDropDownItemSelected = { province ->
                                viewModel.calculateProvincialTaxes(province)
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
                            HST = viewModel.hstAmount.value,
                            totalAmount = viewModel.provTotalAmount.value
                        )
                    }
                    "Budget" -> {
                        EditBudgetRate(
                            value = viewModel.enterTax.value ,
                            onValueChange = {value ->
                                viewModel.enterTax.value = value
                                viewModel.calculateBudget()
                            }
                        )
                        BudgetResultsScreen(
                            taxAmount = viewModel.totalAmount.value,
                            budgetAmount = viewModel.totalAmount.value,
                            modifier = Modifier
                                .padding(top = 4.dp)
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