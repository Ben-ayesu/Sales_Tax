package salestaxcalculator.example.bcsalestax.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import salestaxcalculator.example.bcsalestax.data.Item
import salestaxcalculator.example.bcsalestax.ui.Screens.SalesTaxViewModel

@Composable
fun ExtendedActionButton(
    salesTaxViewModel: SalesTaxViewModel
) {
    ExtendedFloatingActionButton(
        modifier = Modifier
            .padding(8.dp)
            .clip(CircleShape),
        containerColor = FloatingActionButtonDefaults.containerColor,
        onClick = {
            val newItem = Item(
                totalWTax = salesTaxViewModel.totalAmount.value,
                salesTaxViewModel.taxAmount.value
            )
            salesTaxViewModel.addItem(newItem)
        }
    ) {
        Icon(Icons.Filled.Add, contentDescription = "Add Item")
        Text("Add Item")
    }
}