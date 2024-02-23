package salestaxcalculator.example.bcsalestax.ui.Screens

import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import salestaxcalculator.example.bcsalestax.data.Province
import salestaxcalculator.example.bcsalestax.data.USState

class BudgetViewModel : ViewModel() {

    // Enter Budget and enter tax
    val enterBudget = mutableStateOf("")
    val enterTax = mutableStateOf("")

    // Amount to be updated on results budget view
    val maxItemAmount = mutableDoubleStateOf(0.0)
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
    val radioOptions = listOf("🌎 Custom Tax", "🇨🇦 Canada", "🇺🇸 USA")

    // initial selected option
    var selectedOptions = radioOptions.first()
    val selectedOptionState = mutableStateOf(selectedOptions)

    val onOptionSelected: (String) -> Unit = { selectedOption ->
        selectedOptionState.value = selectedOption
    }
    fun calculateBudget() {
        val budget = enterBudget.value.toDoubleOrNull() ?: 0.0
        val taxRate = enterTax.value.toDoubleOrNull() ?: 0.0

        maxItemAmount.doubleValue = calculateMaxItemAmount(budget, taxRate)
        maxTaxAmount.value = calculateMaxItemTax(maxItemAmount.doubleValue, taxRate)
    }

    fun calculateMaxItemAmount(budget: Double, taxRate: Double): Double {
        val taxMultiplier = 1 + (taxRate / 100.0)
        return budget / taxMultiplier
    }

    fun calculateMaxItemTax(maxItemAmount: Double, taxRate: Double): Double {
        return maxItemAmount * (taxRate / 100.0)
    }

    fun calculateProvincialBudget(province: Province) {
        val amount = enterBudget.value.toDoubleOrNull() ?: 0.00
        gstAmount.value = maxItemAmount.doubleValue * (province.gst / 100.0)
        pstAmount.value = maxItemAmount.doubleValue * (province.pst / 100.0)
        hstAmount.value = maxItemAmount.doubleValue * (province.hst / 100.0)
        provMaxBudgetWithoutTax.value =
            (amount) - (pstAmount.value + gstAmount.value + hstAmount.value)
        println("gst: ${gstAmount.value}, item: ${provMaxBudgetWithoutTax.value}")
    }

    fun calculateStateTaxes(state: USState) {
        statesTaxAmount.value = maxItemAmount.doubleValue * (state.taxRate / 100.0)
        statesTotalAmountWithoutTax.value =
            (enterBudget.value.toDoubleOrNull() ?: 0.0) - statesTaxAmount.value
    }
}