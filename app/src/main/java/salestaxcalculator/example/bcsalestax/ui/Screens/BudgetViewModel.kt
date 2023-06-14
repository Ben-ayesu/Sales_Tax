package salestaxcalculator.example.bcsalestax.ui.Screens

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import salestaxcalculator.example.bcsalestax.data.Province
import salestaxcalculator.example.bcsalestax.data.USState

class BudgetViewModel : ViewModel() {

    // Enter Budget and enter tax
    val enterBudget = mutableStateOf("")
    val enterTax = mutableStateOf("")

    // Amount to be updated on results budget view
    val maxItemAmount = mutableStateOf(0.0)
    var maxTaxAmount = mutableStateOf(0.0)

    // provincial tax calculation and view
    val pstAmount = mutableStateOf(0.00)
    val gstAmount = mutableStateOf(0.00)
    val hstAmount = mutableStateOf(0.00)
    val provMaxBudgetWithoutTax = mutableStateOf(0.0)

    // state tax
    val statesTaxAmount = mutableStateOf(0.0)
    val statesTotalAmountWithoutTax = mutableStateOf(0.0)

    // List for the radio button options
    val radioOptions = listOf("Custom Tax", "Canada", "United States")

    // initial selected option
    val selectedOptions = radioOptions.first()
    fun calculateBudget() {
        val budget = enterBudget.value.toDoubleOrNull() ?: 0.0
        val taxRate = enterTax.value.toDoubleOrNull() ?: 0.0

        maxItemAmount.value = calculateMaxItemAmount(budget, taxRate)
        maxTaxAmount.value = calculateMaxItemTax(maxItemAmount.value, taxRate)
    }

    fun calculateMaxItemAmount(budget: Double, taxRate: Double): Double {
        val taxMultiplier = 1 + (taxRate / 100.0)
        return budget / taxMultiplier
    }

    fun calculateMaxItemTax(maxItemAmount: Double, taxRate: Double): Double {
        return maxItemAmount * (taxRate / 100.0)
    }

    fun calculateProvincialBudget(province: Province) {
        gstAmount.value = maxItemAmount.value * (province.GST / 100.0)
        pstAmount.value = maxItemAmount.value * (province.PST / 100.0)
        hstAmount.value = maxItemAmount.value * (province.HST / 100.0)
        provMaxBudgetWithoutTax.value = (enterBudget.value.toDoubleOrNull()
            ?: 0.0) - (pstAmount.value + gstAmount.value + hstAmount.value)
    }

    fun calculateStateTaxes(state: USState) {
        statesTaxAmount.value = maxItemAmount.value * (state.taxRate / 100.0)
        statesTotalAmountWithoutTax.value =
            (enterBudget.value.toDoubleOrNull() ?: 0.0) - statesTaxAmount.value
    }
}