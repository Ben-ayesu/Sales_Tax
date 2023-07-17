
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import salestaxcalculator.example.bcsalestax.ui.Screens.SalesTaxViewModel
import salestaxcalculator.example.bcsalestax.ui.components.ItemRow

@Composable
fun BottomSheetScreen(viewModel: SalesTaxViewModel) {
    val showModalBottomSheet = rememberSaveable { mutableStateOf(false) }

    Button(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp), onClick = {
        showModalBottomSheet.value = !showModalBottomSheet.value
    }) {
        Text(text = "Show List of Items")
    }
    ModalBottomSheet(showModalBottomSheet, viewModel)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalBottomSheet(
    showModalBottomSheet: MutableState<Boolean>,
    viewModel: SalesTaxViewModel
) {
    val skipPartially by remember { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = skipPartially)

    if (showModalBottomSheet.value) ModalBottomSheet(
        onDismissRequest = { showModalBottomSheet.value = false },
        sheetState = bottomSheetState,
    ) {
        Column(
            Modifier.fillMaxSize()
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Item List",
                letterSpacing = 5.sp,
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Divider(modifier = Modifier.padding(8.dp))
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                // Item List with Total Cost and Associated Tax
                Text(
                    text = "Total W/ Tax: \$${"%.2f".format(viewModel.itemList.sumOf { it.totalWTax + (it.totalWTax * (it.tax / 100)) })}       Tax: \$${
                        "%.2f".format(
                            viewModel.itemList.sumOf { (it.tax / 100) * it.totalWTax })
                    }",
                    style = MaterialTheme.typography.headlineSmall
                )
                println("\$${viewModel.itemList.sumOf { it.totalWTax + (it.totalWTax * (it.tax / 100)) }}")
            }
            Spacer(modifier = Modifier.height(16.dp))
            // Header for "Amount"
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset((-14).dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "Amount ($)",
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Tax ($)",
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold
                )
            }
            LazyColumn {
                items(viewModel.itemList) { item ->
                    ItemRow(
                        item = item,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}