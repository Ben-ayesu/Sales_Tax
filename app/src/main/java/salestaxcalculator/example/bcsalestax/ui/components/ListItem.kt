package salestaxcalculator.example.bcsalestax.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import salestaxcalculator.example.bcsalestax.data.Item
import salestaxcalculator.example.bcsalestax.ui.Screens.SalesTaxViewModel

@Composable
fun ItemRow(item: Item, viewModel: SalesTaxViewModel) {
    Row(
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp),
    ) {
        Text(
            text = "Item:",
            modifier = Modifier.padding(top = 8.dp, start = 8.dp, bottom = 8.dp)
        )
        ItemPriceWTotal(item)
        DeleteButton(viewModel)
    }
}

@Composable
fun ItemPriceWTotal(item: Item) {
    Text(
        text = "    \$${(item.totalWTax + (item.totalWTax / 100))}               \$${((item.totalWTax) * (item.totalWTax / 100))}",
        modifier = Modifier.padding(top = 9.dp, end = 16.dp, bottom = 8.dp, start = 30.dp)
    )
}

@Composable
fun DeleteButton(viewModel: SalesTaxViewModel) {
    if (viewModel.itemList.isNotEmpty()) {
        FilledIconButton(
            onClick = {
                viewModel.itemList.removeAt(viewModel.itemList.lastIndex)
            }
        ) {
            Icon(
                Icons.Outlined.Delete,
                contentDescription = "Delete"
            )
        }
    }
}
