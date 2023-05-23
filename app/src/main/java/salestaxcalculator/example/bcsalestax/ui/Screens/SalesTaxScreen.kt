package salestaxcalculator.example.bcsalestax.ui.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import salestaxcalculator.example.bcsalestax.data.USStates
import salestaxcalculator.example.bcsalestax.data.provinces
import salestaxcalculator.example.bcsalestax.ui.components.CustomTaxResultsView
import salestaxcalculator.example.bcsalestax.ui.components.EditItemNumberField
import salestaxcalculator.example.bcsalestax.ui.components.EditTaxRate
import salestaxcalculator.example.bcsalestax.ui.components.ProvincialTaxResultsView
import salestaxcalculator.example.bcsalestax.ui.components.SearchableExpandedDropDownMenu
import salestaxcalculator.example.bcsalestax.ui.components.SelectRow
import salestaxcalculator.example.bcsalestax.ui.components.StateTaxResultsView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SalesTaxScreen(viewModel: SalesTaxViewModel) {

    val selectedOptionState = remember { mutableStateOf(viewModel.selectedOptions) }

    val onOptionSelected: (String) -> Unit = { selectedOption ->
        selectedOptionState.value = selectedOption
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 150.dp)
    ) {
        //Enter Item Price Text Field
        EditItemNumberField(
            viewModel.enterItemPrice.value
        ) { value ->
            viewModel.enterItemPrice.value = value
            viewModel.calculateAmounts()
        }
        SelectRow(
            viewModel.radioOptions,
            modifier = Modifier
                .padding(top = 16.dp, bottom = 16.dp)
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
                        .padding(vertical = 8.dp)
                )
            }

            "Canada" -> {
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
                    pst = viewModel.pstAmount.value,
                    gst = viewModel.gstAmount.value,
                    hst = viewModel.hstAmount.value,
                    totalAmount = viewModel.provTotalAmount.value,
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                )
            }

            "United States" -> {
                SearchableExpandedDropDownMenu(
                    listOfItems = USStates,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    onDropDownItemSelected = { state ->
                        viewModel.calculateStateTaxes(state)
                    },
                    placeholder = "Select State",
                    openedIcon = Icons.Outlined.ArrowDropDown,
                    closedIcon = Icons.Outlined.KeyboardArrowUp,
                    dropdownItem = { state ->
                        Text(text = state.stateName)
                    },
                )
                StateTaxResultsView(
                    taxAmount = viewModel.statesTaxAmount.value,
                    totalAmount = viewModel.statesTotalAmount.value,
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                )
            }

            else -> {
                // Nothing
            }
        }
    }
}