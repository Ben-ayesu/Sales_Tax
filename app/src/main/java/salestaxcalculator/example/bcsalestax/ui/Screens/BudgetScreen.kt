package salestaxcalculator.example.bcsalestax.ui.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.bcsalestax.R
import salestaxcalculator.example.bcsalestax.data.USStates
import salestaxcalculator.example.bcsalestax.data.provinces
import salestaxcalculator.example.bcsalestax.ui.components.CustomTaxResultsView
import salestaxcalculator.example.bcsalestax.ui.components.ProvincialResultsView
import salestaxcalculator.example.bcsalestax.ui.components.SearchableExpandedDropDownMenu
import salestaxcalculator.example.bcsalestax.ui.components.SelectRow
import salestaxcalculator.example.bcsalestax.ui.components.TextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BudgetScreen(viewModel: BudgetViewModel) {

    val selectedOptionState = remember { mutableStateOf(viewModel.selectedOptions) }

    val onOptionSelected: (String) -> Unit = { selectedOption ->
        selectedOptionState.value = selectedOption
    }

    val state = rememberScrollState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(state)
            .padding(top = 42.dp)
    ) {
        TextField(
            value = viewModel.enterBudget.value,
            "Enter your Budget",
            "$",
            onValueChange = { value ->
                viewModel.enterBudget.value = value
                viewModel.calculateBudget()
            },
            modifier = Modifier.padding(top = 24.dp, start = 16.dp, end = 16.dp, bottom = 8.dp),
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
                TextField(
                    modifier = Modifier.padding(
                        top = 8.dp,
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 8.dp
                    ),
                    value = viewModel.enterTax.value,
                    label = "Enter Tax Rate",
                    leadingIcon = "%",
                    onValueChange = { value ->
                        viewModel.enterTax.value = value
                        viewModel.calculateBudget()
                    }
                )
                CustomTaxResultsView(
                    totalAmount = viewModel.maxItemAmount.value,
                    taxAmount = viewModel.maxTaxAmount.value,
                    labelResId = R.string.budget_amount_label,
                    itemAmount = viewModel.enterBudget.value.toDoubleOrNull() ?: 0.00,
                    modifier = Modifier
                        .padding(16.dp)
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
                ProvincialResultsView(
                    pst = viewModel.pstAmount.value,
                    gst = viewModel.gstAmount.value,
                    hst = viewModel.hstAmount.value,
                    amount = viewModel.enterBudget.value.toDoubleOrNull() ?: 0.00,
                    totalAmountText = "Budget Amount Without Tax:",
                    totalAmount = viewModel.provMaxBudgetWithoutTax.value,
                    modifier = Modifier
                        .padding(16.dp)
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
                CustomTaxResultsView(
                    taxAmount = viewModel.statesTaxAmount.value,
                    totalAmount = viewModel.statesTotalAmountWithoutTax.value,
                    labelResId = R.string.budget_amount_label,
                    itemAmount = viewModel.enterBudget.value.toDouble(),
                    modifier = Modifier
                        .padding(16.dp)
                )
            }
            else -> {
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