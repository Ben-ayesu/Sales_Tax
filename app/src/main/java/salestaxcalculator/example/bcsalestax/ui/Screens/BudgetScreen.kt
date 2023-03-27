package salestaxcalculator.example.bcsalestax.ui.Screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

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

@Preview
@Composable
fun BudgetScreenPreview() {
    BudgetScreen()
}