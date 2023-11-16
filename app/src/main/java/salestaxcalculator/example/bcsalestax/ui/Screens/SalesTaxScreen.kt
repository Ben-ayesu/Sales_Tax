package salestaxcalculator.example.bcsalestax.ui.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bcsalestax.R
import salestaxcalculator.example.bcsalestax.data.USStates
import salestaxcalculator.example.bcsalestax.data.provinces
import salestaxcalculator.example.bcsalestax.ui.components.AddtoListButton
import salestaxcalculator.example.bcsalestax.ui.components.CalculateButton
import salestaxcalculator.example.bcsalestax.ui.components.CustomTaxResultsView
import salestaxcalculator.example.bcsalestax.ui.components.ProvincialResultsView
import salestaxcalculator.example.bcsalestax.ui.components.SearchableExpandedDropDownMenu
import salestaxcalculator.example.bcsalestax.ui.components.SelectRow
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
        //Item Price Text Field
        TextField(
            modifier = Modifier.padding(top = 70.dp, start = 16.dp, end = 16.dp, bottom = 8.dp),
            value = viewModel.priceInput.value,
            label = "Enter Item Price",
            leadingIcon = "$",
            onValueChange = { value ->
                viewModel.priceInput.value = value
                viewModel.calculateAmounts()
            }
        )
        SelectRow(
            viewModel.radioOptions,
            modifier = Modifier
                .padding(top = 16.dp, bottom = 16.dp)
                .align(Alignment.Start),
            viewModel.selectedOptionState.value,
            viewModel.onOptionSelected
        )
        when (viewModel.selectedOptionState.value) {
            "\uD83C\uDF0E Custom Tax" -> {
                TextField(
                    modifier = Modifier.padding(
                        top = 8.dp,
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 8.dp
                    ),
                    value = viewModel.taxInput.value,
                    label = "Enter Tax Rate",
                    leadingIcon = "%",
                    onValueChange = { value ->
                        viewModel.taxInput.value = value
                        viewModel.calculateAmounts()
                    }
                )
                CustomTaxResultsView(
                    taxAmount = viewModel.taxAmount.value,
                    totalAmount = viewModel.totalAmount.value,
                    itemAmount = viewModel.priceInput.value.toDoubleOrNull() ?: 0.00,
                    labelResId = R.string.total_amount_label,
                    modifier = Modifier
                        .padding(16.dp)
                )
                Divider(modifier = Modifier.padding(16.dp))
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    AddtoListButton(
                        viewModel = viewModel,
                        snackbarHostState = snackbarHostState,
                        viewModel.validateInput()
                    )
                    CalculateButton(
                        salesTaxViewModel = viewModel,
                        isValid = viewModel.validateInput()
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    IconButton(
                        onClick = {

                        },
                    ) {
                        Icon(imageVector = Icons.Default.Info, contentDescription = "Info")
                    }
                    Text(text = "Tap icon in the top right to show item list")
                }
            }

            "\uD83C\uDDE8\uD83C\uDDE6 Canada" -> {
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
                ProvincialResultsView(
                    pst = viewModel.pstAmount.value,
                    gst = viewModel.gstAmount.value,
                    hst = viewModel.hstAmount.value,
                    amount = viewModel.priceInput.value.toDoubleOrNull() ?: 0.00,
                    totalAmountText = "Total Amount With Tax:",
                    totalAmount = viewModel.provTotalAmount.value,
                    modifier = Modifier
                        .padding(16.dp)
                )
            }

            "\uD83C\uDDFA\uD83C\uDDF8 United States" -> {
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
                    totalAmount = viewModel.statesTotalAmount.value,
                    itemAmount = viewModel.priceInput.value.toDoubleOrNull() ?: 0.00,
                    labelResId = R.string.total_amount_label,
                    modifier = Modifier
                        .padding(16.dp)
                )
            }

            else -> {
                // Nothing
            }
        }
        Spacer(modifier = Modifier.height(100.dp))
    }
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