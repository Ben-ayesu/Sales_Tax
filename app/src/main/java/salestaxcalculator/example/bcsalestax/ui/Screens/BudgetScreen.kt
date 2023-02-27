package salestaxcalculator.example.bcsalestax.ui.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import salestaxcalculator.example.bcsalestax.ui.components.BudgetResultsScreen
import salestaxcalculator.example.bcsalestax.ui.components.EditBudgetRate

@Composable
fun BudgetScreen() {
    Text(text = "Hello World")
//    Column() {
//        EditBudgetRate(
//            value = viewModel.enterTax.value ,
//            onValueChange = {value ->
//                viewModel.enterTax.value = value
//                viewModel.calculateBudget()
//            }
//        )
//        BudgetResultsScreen(
//            taxAmount = viewModel.totalAmount.value,
//            budgetAmount = viewModel.totalAmount.value,
//            modifier = Modifier
//                .padding(top = 4.dp)
//        )
//    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BudgetScreenPreview() {
    BudgetScreen()
}