package salestaxcalculator.example.bcsalestax.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import salestaxcalculator.example.bcsalestax.data.Item
import salestaxcalculator.example.bcsalestax.ui.Screens.SalesTaxViewModel

@Composable
fun AddtoListButton(
    salesTaxViewModel: SalesTaxViewModel,
    snackbarHostState: SnackbarHostState
) {
    val coroutineScope = rememberCoroutineScope()

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(CircleShape),
        onClick = {
            val newItem = Item(
                totalWTax = salesTaxViewModel.totalAmount.value,
                salesTaxViewModel.taxAmount.value
            )
            salesTaxViewModel.addItem(newItem)
            coroutineScope.launch {
                snackbarHostState.showSnackbar("Item has been added")
            }
        }
    ) {
        Icon(Icons.Filled.Add, contentDescription = "Add Item")
        Text("Add Item")
    }
}