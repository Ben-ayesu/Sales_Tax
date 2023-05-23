package salestaxcalculator.example.bcsalestax.ui.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import salestaxcalculator.example.bcsalestax.ui.components.BudgetResultsScreen
import salestaxcalculator.example.bcsalestax.ui.components.EditBudgetRate
import salestaxcalculator.example.bcsalestax.ui.components.EditTaxRate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BudgetScreen(viewModel: BudgetViewModel) {


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 150.dp)
    ) {
        EditBudgetRate(
            value = viewModel.enterbudgetTotal.value,
            onValueChange = { value ->
                viewModel.enterbudgetTotal.value = value
                viewModel.calculateBudget()
            }
        )
        Spacer(modifier = Modifier.padding(8.dp))
        EditTaxRate(
            value = viewModel.enterTax.value,
            onValueChange = { value ->
                viewModel.enterTax.value = value
                viewModel.calculateBudget()
            },
        )
        Spacer(modifier = Modifier.padding(0.dp))
        BudgetResultsScreen(
            budgetAmount = viewModel.maxItemAmount.value,
            taxAmount = viewModel.maxTaxamount.value,
        )
    }
}

@Preview
@Composable
fun BudgetScreenPreview() {
    val viewModel = BudgetViewModel()
    BudgetScreen(viewModel = viewModel)
}