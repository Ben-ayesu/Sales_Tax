
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import salestaxcalculator.example.bcsalestax.ui.Screens.SalesTaxViewModel

@Composable
fun BottomSheetScreen(viewModel: SalesTaxViewModel) {
    val showModalBottomSheet = rememberSaveable { mutableStateOf(false) }

    Button(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        onClick = {
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
    var skipPartially by remember { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = skipPartially)

    if (showModalBottomSheet.value)
        ModalBottomSheet(
            onDismissRequest = { showModalBottomSheet.value = false },
            sheetState = bottomSheetState,
        ) {
            Column(Modifier.fillMaxSize()) {
                // Header for "Amount"
                Text(
                    text = "Amount",
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 85.dp),
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold
                )
                // Header for "Tax"
                Text(
                    text = "Tax",
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 4.dp),
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold
                )
                Row(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    // Item List with Total Cost and Associated Tax
                    Text(
                        text = "Totals:          \$${viewModel.itemList.sumOf { it.totalWTax }}               \$${viewModel.itemList.sumOf { it.tax }}",
                        modifier = Modifier
                            .padding(top = 16.dp, end = 16.dp, bottom = 16.dp, start = 16.dp)
                    )
                }
            }
        }
}