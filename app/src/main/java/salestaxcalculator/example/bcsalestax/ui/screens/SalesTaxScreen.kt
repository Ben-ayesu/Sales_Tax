package salestaxcalculator.example.bcsalestax.ui.screens

import ChipsRow
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bcsalestax.R
import salestaxcalculator.example.bcsalestax.data.usStates
import salestaxcalculator.example.bcsalestax.data.provinces
import salestaxcalculator.example.bcsalestax.ui.components.AddtoListButton
import salestaxcalculator.example.bcsalestax.ui.components.CustomTaxResultsView
import salestaxcalculator.example.bcsalestax.ui.components.ProvincialResultsView
import salestaxcalculator.example.bcsalestax.ui.components.SearchableExpandedDropDownMenu
import salestaxcalculator.example.bcsalestax.ui.components.TextField

@Composable
fun SalesTaxScreen(
    viewModel: SalesTaxViewModel,
    snackbarHostState: SnackbarHostState,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding()
            .verticalScroll(rememberScrollState())
    ) {
        TopScreenSection(viewModel = viewModel)
        TaxCalculationSection(viewModel = viewModel, snackbarHostState = snackbarHostState)
    }
}

@Composable
fun TopScreenSection(viewModel: SalesTaxViewModel) {
    //Item price text field
    TextField(
        modifier = Modifier.padding(top = 70.dp, start = 16.dp, end = 16.dp, bottom = 8.dp),
        value = viewModel.priceInput.value,
        label = stringResource(R.string.enter_item_price),
        leadingIcon = "$",
        onValueChange = { value ->
            viewModel.priceInput.value = value
            viewModel.calculateAmounts()
        }
    )
    // Chips row
    ChipsRow(
        chips = viewModel.radioOptions,
        selectedChip = viewModel.selectedOptionState.value,
        onChipSelected = viewModel.handleOptionSelected
    )
}

@Composable
fun TaxCalculationSection(viewModel: SalesTaxViewModel, snackbarHostState: SnackbarHostState) {
    // If one of the chip rows is selected, Displays the appropriate fields and results view
    when (viewModel.selectedOptionState.value) {
        stringResource(R.string.custom_tax) -> CustomTaxSection(
            viewModel = viewModel,
            snackbarHostState = snackbarHostState
        )
        stringResource(R.string.canada) -> CanadaTaxSection(viewModel = viewModel)
        stringResource(R.string.usa) -> USATaxSection(viewModel = viewModel)
    }
}

@Composable
fun CustomTaxSection(viewModel: SalesTaxViewModel, snackbarHostState: SnackbarHostState) {
    // Input for custom tax rate
    TextField(
        modifier = Modifier.padding(
            top = 8.dp,
            start = 16.dp,
            end = 16.dp,
            bottom = 8.dp
        ),
        value = viewModel.taxInput.value,
        label = stringResource(id = R.string.enter_tax_rate),
        leadingIcon = "%",
        onValueChange = { value ->
            viewModel.taxInput.value = value
            viewModel.calculateAmounts()
        }
    )
    // Results view
    CustomTaxResultsView(
        taxAmount = viewModel.taxAmount.doubleValue,
        totalAmount = viewModel.totalAmount.doubleValue,
        itemAmount = viewModel.priceInput.value.toDoubleOrNull() ?: 0.00,
        taxRate = viewModel.taxInput.value.toDoubleOrNull() ?: 0.00,
        modifier = Modifier
            .padding(16.dp)
    )
    HorizontalDivider(modifier = Modifier.padding(16.dp))
    Spacer(modifier = Modifier.padding(vertical = 8.dp))
    AddtoListButton(
        viewModel = viewModel,
        snackbarHostState = snackbarHostState,
        viewModel.validateInput()
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(onClick = {}) {
            Icon(imageVector = Icons.Default.Info, contentDescription = "Info")
        }
        Text(text = stringResource(R.string.tip_tool_text))
    }
}

@Composable
fun CanadaTaxSection(viewModel: SalesTaxViewModel) {
    // Drop down for Canada taxes
    SearchableExpandedDropDownMenu(
        listOfItems = provinces,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        onDropDownItemSelected = { province ->
            viewModel.calculateProvincialTaxes(province)
        },
        placeholder = stringResource(id = R.string.select_province),
        openedIcon = Icons.Outlined.ArrowDropDown,
        closedIcon = Icons.Outlined.KeyboardArrowUp,
        dropdownItem = { province ->
            Text(text = province.name)
        },
    )
    // Provincial results view
    ProvincialResultsView(
        pst = viewModel.pstAmount.doubleValue,
        gst = viewModel.gstAmount.doubleValue,
        hst = viewModel.hstAmount.doubleValue,
        amount = viewModel.priceInput.value.toDoubleOrNull() ?: 0.00,
        totalAmount = viewModel.provTotalAmount.doubleValue,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Composable
fun USATaxSection(viewModel: SalesTaxViewModel) {
    // Drop down for USA taxes
    SearchableExpandedDropDownMenu(
        listOfItems = usStates,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        onDropDownItemSelected = { state ->
            viewModel.calculateStateTaxes(state)
        },
        placeholder = stringResource(id = R.string.select_state),
        openedIcon = Icons.Outlined.ArrowDropDown,
        closedIcon = Icons.Outlined.KeyboardArrowUp,
        dropdownItem = { state ->
            Text(text = state.name)
        },
    )
    // USA results view
    CustomTaxResultsView(
        taxAmount = viewModel.statesTaxAmount.doubleValue,
        totalAmount = viewModel.statesTotalAmount.doubleValue,
        itemAmount = viewModel.priceInput.value.toDoubleOrNull() ?: 0.00,
        taxRate = viewModel.taxRate.doubleValue,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Preview
@Composable
fun SalesTaxScreenPreview() {
    val viewModel = SalesTaxViewModel()
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    SalesTaxScreen(
        viewModel = viewModel,
        snackbarHostState = snackbarHostState,
    )
}