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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import salestaxcalculator.example.bcsalestax.data.USStates
import salestaxcalculator.example.bcsalestax.data.provinces
import salestaxcalculator.example.bcsalestax.ui.components.BudgetCustomResultsView
import salestaxcalculator.example.bcsalestax.ui.components.EditBudgetRate
import salestaxcalculator.example.bcsalestax.ui.components.EditTaxRate
import salestaxcalculator.example.bcsalestax.ui.components.ProvincialBudgetResultsView
import salestaxcalculator.example.bcsalestax.ui.components.SearchableExpandedDropDownMenu
import salestaxcalculator.example.bcsalestax.ui.components.SelectRow
import salestaxcalculator.example.bcsalestax.ui.components.StateTaxResultsView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BudgetScreen(viewModel: BudgetViewModel) {

    val selectedOptionState = remember { mutableStateOf(viewModel.selectedOptions) }

    val onOptionSelected: (String) -> Unit = { selectedOption ->
        selectedOptionState.value = selectedOption
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 150.dp)
    ) {
        EditBudgetRate(
            value = viewModel.enterBudget.value,
            onValueChange = { value ->
                viewModel.enterBudget.value = value
                viewModel.calculateBudget()
            }
        )
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
                    viewModel.calculateBudget()
                }
                BudgetCustomResultsView(
                    budgetAmount = viewModel.maxItemAmount.value,
                    taxAmount = viewModel.maxTaxAmount.value,
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
                        viewModel.calculateProvincialBudget(province)
                    },
                    placeholder = "Select Province",
                    openedIcon = Icons.Outlined.ArrowDropDown,
                    closedIcon = Icons.Outlined.KeyboardArrowUp,
                    dropdownItem = { province ->
                        Text(text = province.provinceName)
                    },
                )
                ProvincialBudgetResultsView(
                    pst = viewModel.pstAmount.value,
                    gst = viewModel.gstAmount.value,
                    hst = viewModel.hstAmount.value,
                    budgetAmount = viewModel.provMaxBudgetWithoutTax.value
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
                    totalAmount = viewModel.statesTotalAmountWithoutTax.value,
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

@Preview
@Composable
fun BudgetScreenPreview() {
    val viewModel = BudgetViewModel()
    BudgetScreen(viewModel = viewModel)
}