package salestaxcalculator.example.bcsalestax.ui.Screens

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class BudgetViewModel : ViewModel() {

    val budgetTotal = mutableStateOf("")
    val enterTax = mutableStateOf("")
    fun calculateBudget() {
        val budget = budgetTotal.value?.toDoubleOrNull() ?: 0.00
        val tax = enterTax.value?.toDoubleOrNull() ?: 0.00
        calculateBudgetAmount(budget, tax)
    }

    //Calculates item price based on budget and tax entered
    private fun calculateBudgetAmount(
        budget: Double,
        taxAmount: Double
    ): Double {
        return (budget / (1 + (taxAmount / 100)))
    }
}