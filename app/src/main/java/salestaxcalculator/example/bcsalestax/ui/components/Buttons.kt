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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bcsalestax.R
import kotlinx.coroutines.launch
import salestaxcalculator.example.bcsalestax.data.Item
import salestaxcalculator.example.bcsalestax.ui.screens.SalesTaxViewModel

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
                    priceIncludingTax = viewModel.priceInput.value.toDouble(),
                    tax = viewModel.taxInput.value.toDouble()
                )
                viewModel.addItem(newItem)
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        withDismissAction = true,
                        message = "Item has been added",
                        duration = SnackbarDuration.Short,
                    )
                }
                keyboardController?.hide()
            } else {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        withDismissAction = true,
                        message = "Invalid Input. Please enter a valid price and tax",
                        duration = SnackbarDuration.Short
                    )
                }
            }
        }
    ) {
        Icon(Icons.Filled.Add, contentDescription = "Add Item")
        Text(stringResource(R.string.add_to_list))
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