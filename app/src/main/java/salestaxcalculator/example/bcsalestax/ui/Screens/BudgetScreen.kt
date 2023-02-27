package salestaxcalculator.example.bcsalestax.ui.Screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BudgetScreen() {
    Text(text = "Hello Second Screen")
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BudgetScreenPreview() {
    BudgetScreen()
}