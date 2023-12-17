package salestaxcalculator.example.bcsalestax.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import salestaxcalculator.example.bcsalestax.data.Item
import salestaxcalculator.example.bcsalestax.ui.Screens.SalesTaxViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddtoListButton(
    viewModel: SalesTaxViewModel,
    snackbarHostState: SnackbarHostState,
    isValid: Boolean
) {
    val coroutineScope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(CircleShape),
        onClick = {
            if (isValid) {
                val newItem = Item(
                    id = viewModel.itemIndex,
                    totalWTax = viewModel.priceInput.value.toDouble(),
                    tax = viewModel.taxInput.value.toDouble()
                )
                viewModel.addItem(newItem)
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        withDismissAction = true,
                        message = "An item has been added",
                        duration = SnackbarDuration.Short,
                    )
                }
                keyboardController?.hide()
            } else {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        withDismissAction = true,
                        message = "Please enter a valid price and tax",
                        duration = SnackbarDuration.Short
                    )
                }
            }
        }
    ) {
        Icon(Icons.Filled.Add, contentDescription = "Add Item")
        Text("Add to list")
    }
}

@Composable
fun CalculateButton(
    salesTaxViewModel: SalesTaxViewModel,
    isValid: Boolean
) {
    Button(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .clip(CircleShape),
        onClick = {
            if (isValid) {
                salesTaxViewModel.calculateAmounts()
            }
        }
    ) {
        Text(text = "Calculate")
    }
}


@Preview
@Composable
fun AddToListButtonPreview() {
    AddtoListButton(
        viewModel = SalesTaxViewModel(),
        snackbarHostState = SnackbarHostState(),
        isValid = true
    )
}

@Preview
@Composable
fun CalculateButtonPreview() {
    CalculateButton(
        salesTaxViewModel = SalesTaxViewModel(),
        isValid = true
    )
}