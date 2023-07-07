package salestaxcalculator.example.bcsalestax.ui.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material3.Divider
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
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

@Composable
fun SalesTaxScreen(viewModel: SalesTaxViewModel) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 80.dp)
            .verticalScroll(rememberScrollState())
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
            viewModel.selectedOptionState.value,
            viewModel.onOptionSelected
        )
        when (viewModel.selectedOptionState.value) {
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
                        .padding(vertical = 8.dp)
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
                        .padding(vertical = 8.dp)
                )
            }

            else -> {
                // Nothing
            }
        }
        Divider()
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
            ) {
                // Header for "Amount"
                Text(
                    text = "Amount",
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 16.dp, start = 90.dp, bottom = 8.dp),
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold
                )
                // Header for "Tax"
                Text(
                    text = "Tax",
                    modifier = Modifier
                        .weight(2f)
                        .padding(vertical = 16.dp),
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold
                )
                FilledIconButton(
                    modifier = Modifier
                        .padding(8.dp),
                    onClick = {
                        viewModel.itemList.removeAt(viewModel.itemList.lastIndex)
                    }
                ) {
                    Icon(Icons.Outlined.Delete, contentDescription = "Delete")
                }
            }
            viewModel.itemList.forEach { _ ->
                Row {
                    Text(
                        text = "Item:",
                        modifier = Modifier
                            .padding(top = 16.dp, start = 8.dp, bottom = 8.dp)
                    )
                    Text(
                        text = "    \$${viewModel.totalAmount.value}                \$${viewModel.taxAmount.value}",
                        modifier = Modifier
                            .padding(top = 16.dp, end = 16.dp, bottom = 8.dp, start = 32.dp)
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                // Item List with Total Cost and Associated Tax
                Text(
                    text = "Totals:          \$${viewModel.itemList.sumOf { it.totalWTax }}                \$${viewModel.itemList.sumOf { it.tax }}",
                    modifier = Modifier
                        .padding(top = 16.dp, end = 16.dp, bottom = 16.dp, start = 8.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun SalesTaxScreenPreview() {
    val viewModel = SalesTaxViewModel()
    SalesTaxScreen(viewModel = viewModel)
}