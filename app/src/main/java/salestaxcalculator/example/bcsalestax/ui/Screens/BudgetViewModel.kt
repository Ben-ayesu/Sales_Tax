package salestaxcalculator.example.bcsalestax.ui.Screens

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class BudgetViewModel : ViewModel() {

    val enterbudgetTotal = mutableStateOf("")
    val enterTax = mutableStateOf("")

    fun calculateBudget() {
        val budget = enterbudgetTotal.value?.toDoubleOrNull() ?: 0.00
        val tax = enterTax.value?.toDoubleOrNull() ?: 0.00
        calculateMaxItemAmount(budget, tax)
    }

    // Calculates item price based on budget and tax entered
    private fun calculateMaxItemAmount(
        budget: Double,
        taxAmount: Double
    ): Double {
        return (budget / (1 + (taxAmount / 100)))
    }

    // Calculates taxes based on max item price - budget amount
//    private fun calculateTaxesBasedOnItemMax(
//        taxAmount: Double,
//        budget: Double,
//    ) : Double {
//        return ()
//    }
    //Maximum spend = Budget * (1 - Tax rat
}