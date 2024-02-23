package salestaxcalculator.example.bcsalestax.ui.Screens

import ChipsRow
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bcsalestax.R
import salestaxcalculator.example.bcsalestax.data.USStates
import salestaxcalculator.example.bcsalestax.data.provinces
import salestaxcalculator.example.bcsalestax.ui.components.CustomTaxResultsView
import salestaxcalculator.example.bcsalestax.ui.components.ProvincialResultsView
import salestaxcalculator.example.bcsalestax.ui.components.SearchableExpandedDropDownMenu
import salestaxcalculator.example.bcsalestax.ui.components.TextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BudgetScreen(viewModel: BudgetViewModel) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding()
            .verticalScroll(rememberScrollState())
    ) {
        //Item Price Text Field
        TextField(
            modifier = Modifier.padding(top = 70.dp, start = 16.dp, end = 16.dp, bottom = 8.dp),
            value = viewModel.enterBudget.value,
            label = stringResource(R.string.enter_your_budget),
            leadingIcon = "$",
            onValueChange = { value ->
                viewModel.enterBudget.value = value
                viewModel.calculateBudget()
            }
        )
        ChipsRow(
            chips = viewModel.radioOptions,
            selectedChip = viewModel.selectedOptionState.value,
            onChipSelected = viewModel.onOptionSelected
        )
        when (viewModel.selectedOptionState.value) {
            stringResource(R.string.custom_tax) -> {
                TextField(
                    modifier = Modifier.padding(
                        top = 4.dp,
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 4.dp
                    ),
                    value = viewModel.enterTax.value,
                    label = stringResource(R.string.enter_tax_rate),
                    leadingIcon = "%",
                    onValueChange = { value ->
                        viewModel.enterTax.value = value
                        viewModel.calculateBudget()
                    }
                )
                CustomTaxResultsView(
                    totalAmount = viewModel.maxItemAmount.doubleValue,
                    taxAmount = viewModel.maxTaxAmount.value,
                    labelResId = R.string.budget_amount_label,
                    itemAmount = viewModel.enterBudget.value.toDoubleOrNull() ?: 0.00,
                    modifier = Modifier
                        .padding(16.dp)
                )
            }

            stringResource(R.string.canada) -> {
                SearchableExpandedDropDownMenu(
                    listOfItems = provinces,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    onDropDownItemSelected = { province ->
                        viewModel.calculateProvincialBudget(province)
                    },
                    placeholder = stringResource(R.string.select_province),
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
                    totalAmountText = stringResource(R.string.budget_amount_without_tax),
                    totalAmount = viewModel.provMaxBudgetWithoutTax.value,
                    modifier = Modifier
                        .padding(16.dp)
                )
            }

            stringResource(R.string.usa) -> {
                SearchableExpandedDropDownMenu(
                    listOfItems = USStates,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    onDropDownItemSelected = { state ->
                        viewModel.calculateStateTaxes(state)
                    },
                    placeholder = stringResource(R.string.select_state),
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
                    itemAmount = viewModel.enterBudget.value.toDoubleOrNull() ?: 0.00,
                    modifier = Modifier
                        .padding(16.dp)
                )
            }
            else -> {
            }
        }
        Spacer(modifier = Modifier.height(100.dp))
    }
}

@Preview
@Composable
fun BudgetScreenPreview() {
    val viewModel = BudgetViewModel()
    BudgetScreen(viewModel = viewModel)
}