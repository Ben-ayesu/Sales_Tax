package salestaxcalculator.example.bcsalestax.ui.Screens

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class BudgetViewModel : ViewModel() {

    // Enter Budget and enter tax
    val enterbudgetTotal = mutableStateOf("")
    val enterTax = mutableStateOf("")

    // Amount to be updated on results budget view
    val maxItemAmount = mutableStateOf(0.0)
    var maxTaxamount = mutableStateOf(0.0)

    fun calculateBudget() {
        val budget = enterbudgetTotal.value?.toDoubleOrNull() ?: 0.00
        val tax = enterTax.value?.toDoubleOrNull() ?: 0.00
        maxItemAmount.value = calculateMaxItemAmount(budget, tax)
        maxTaxamount.value = budget - maxItemAmount.value
    }

    // Calculates item price based on budget and tax entered
    private fun calculateMaxItemAmount(
        budget: Double,
        taxAmount: Double
    ): Double {
        return (budget / (1 + (taxAmount / 100)))
    }
}