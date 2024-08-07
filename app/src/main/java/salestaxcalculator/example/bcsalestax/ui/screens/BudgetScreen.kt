package salestaxcalculator.example.bcsalestax.ui.screens

import ChipsRow
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bcsalestax.R
import salestaxcalculator.example.bcsalestax.data.usStates
import salestaxcalculator.example.bcsalestax.data.provinces
import salestaxcalculator.example.bcsalestax.ui.components.CustomTaxResultsView
import salestaxcalculator.example.bcsalestax.ui.components.ProvincialResultsView
import salestaxcalculator.example.bcsalestax.ui.components.SearchableExpandedDropDownMenu
import salestaxcalculator.example.bcsalestax.ui.components.TextField

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
        TopScreenSection(viewModel = viewModel)
        TaxCalculationSection(viewModel = viewModel)
    }
}

@Composable
fun TaxCalculationSection(viewModel: BudgetViewModel) {
    // If one of the chip rows is selected, Displays the appropriate fields and results view
    when (viewModel.selectedOptionState.value) {
        stringResource(R.string.custom_tax) -> CustomTaxSection(viewModel = viewModel)
        stringResource(R.string.canada) -> CanadaTaxSection(viewModel = viewModel)
        stringResource(R.string.usa) -> USATaxSection(viewModel = viewModel)
    }
}

@Composable
fun TopScreenSection(viewModel: BudgetViewModel) {
    //Item price text field
    TextField(
        modifier = Modifier.padding(top = 70.dp, start = 16.dp, end = 16.dp, bottom = 8.dp),
        value = viewModel.budgetInput.value,
        label = stringResource(R.string.enter_your_budget),
        leadingIcon = "$",
        onValueChange = { value ->
            viewModel.budgetInput.value = value
            viewModel.calculateBudget()
        }
    )
    ChipsRow(
        chips = viewModel.radioOptions,
        selectedChip = viewModel.selectedOptionState.value,
        onChipSelected = viewModel.onOptionSelected
    )
}

@Composable
fun CustomTaxSection(viewModel: BudgetViewModel) {
    // Input for custom tax rate
    TextField(
        modifier = Modifier.padding(
            top = 4.dp,
            start = 16.dp,
            end = 16.dp,
            bottom = 4.dp
        ),
        value = viewModel.taxInput.value,
        label = stringResource(R.string.enter_tax_rate),
        leadingIcon = "%",
        onValueChange = { value ->
            viewModel.taxInput.value = value
            viewModel.calculateBudget()
        }
    )
    CustomTaxResultsView(
        taxAmount = viewModel.maxTaxAmount.doubleValue,
        totalAmount = viewModel.maxItemPrice.doubleValue,
        itemAmount = viewModel.budgetInput.value.toDoubleOrNull() ?: 0.00,
        taxRate = viewModel.taxInput.value.toDoubleOrNull() ?: 0.00.toDouble(),
        modifier = Modifier
            .padding(16.dp)
    )
}

@Composable
fun CanadaTaxSection(viewModel: BudgetViewModel) {
    // Drop down for Canada taxes
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
            Text(text = province.name)
        },
    )
    ProvincialResultsView(
        pst = viewModel.pstAmount.doubleValue,
        gst = viewModel.gstAmount.doubleValue,
        hst = viewModel.hstAmount.doubleValue,
        amount = viewModel.budgetInput.value.toDoubleOrNull() ?: 0.00,
        totalAmount = viewModel.provMaxBudgetWithoutTax.doubleValue,
        modifier = Modifier
            .padding(16.dp)
    )
}

@Composable
fun USATaxSection(viewModel: BudgetViewModel) {
    // Drop down for USA taxes
    SearchableExpandedDropDownMenu(
        listOfItems = usStates,
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
            Text(text = state.name)
        },
    )
    CustomTaxResultsView(
        taxAmount = viewModel.statesTaxAmount.doubleValue,
        totalAmount = viewModel.statesTotalAmountWithoutTax.doubleValue,
        itemAmount = viewModel.budgetInput.value.toDoubleOrNull() ?: 0.00,
        taxRate = viewModel.taxRate.doubleValue,
        modifier = Modifier
            .padding(16.dp)
    )
}

@Preview
@Composable
fun BudgetScreenPreview() {
    val viewModel = BudgetViewModel()
    BudgetScreen(viewModel = viewModel)
}