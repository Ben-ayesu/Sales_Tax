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
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import salestaxcalculator.example.bcsalestax.data.Item
import salestaxcalculator.example.bcsalestax.ui.Screens.SalesTaxViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddtoListButton(
    viewModel: SalesTaxViewModel,
    snackbarHostState: SnackbarHostState,
) {
    val coroutineScope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(CircleShape),
        onClick = {
            val newItem = Item(
                id = viewModel.itemIndex,
                totalWTax = viewModel.enterItemPrice.value.toDouble(),
                tax = viewModel.enterTax.value.toDouble()
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
        }
    ) {
        Icon(Icons.Filled.Add, contentDescription = "Add Item")
        Text("Add Item")
    }
}