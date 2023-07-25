package salestaxcalculator.example.bcsalestax.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import salestaxcalculator.example.bcsalestax.data.Item
import salestaxcalculator.example.bcsalestax.ui.Screens.SalesTaxViewModel

@Composable
fun ItemRow(
    item: Item,
    viewModel: SalesTaxViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 4.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Item ${item.id}:")
        Text(text = "\$${"%.2f".format(item.totalWTax)}")
        Spacer(modifier = Modifier.width(14.dp))
        Text(text = "\$${"%.2f".format(item.totalWTax * (item.tax / 100))}")
        if (viewModel.itemList.isNotEmpty()) {
            FilledIconButton(
                onClick = {
                    viewModel.deleteItem(item)
                }
            ) {
                Icon(
                    Icons.Outlined.Delete,
                    contentDescription = "Delete"
                )
            }
        }
    }
}
