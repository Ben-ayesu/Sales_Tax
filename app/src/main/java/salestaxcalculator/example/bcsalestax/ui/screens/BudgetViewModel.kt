package salestaxcalculator.example.bcsalestax.ui.screens

import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import salestaxcalculator.example.bcsalestax.data.Province
import salestaxcalculator.example.bcsalestax.data.USState

class BudgetViewModel : ViewModel() {

    // Input values
    val enteredBudget = mutableStateOf("")
    val enteredTax = mutableStateOf("")

    // Calculated results
    val maxItemPrice = mutableDoubleStateOf(0.0)
    val maxTaxAmount = mutableDoubleStateOf(0.0)

    // provincial tax calculation and view
    val pstAmount = mutableDoubleStateOf(0.00)
    val gstAmount = mutableDoubleStateOf(0.00)
    val hstAmount = mutableDoubleStateOf(0.00)
    val provMaxBudgetWithoutTax = mutableDoubleStateOf(0.0)

    // state tax
    val statesTaxAmount = mutableDoubleStateOf(0.0)
    val statesTotalAmountWithoutTax = mutableDoubleStateOf(0.0)

    // List for the radio button options
    val radioOptions = listOf("🌎 Custom Tax", "🇨🇦 Canada", "🇺🇸 USA")

    // initial selected option
    var selectedOptions = radioOptions.first()
    val selectedOptionState = mutableStateOf(selectedOptions)

    val onOptionSelected: (String) -> Unit = { selectedOption ->
        selectedOptionState.value = selectedOption
    }

    fun calculateBudget() {
        val budget = enteredBudget.value.toDoubleOrNull() ?: 0.0
        val taxRate = enteredTax.value.toDoubleOrNull() ?: 0.0
        maxItemPrice.doubleValue = calculateMaxItemAmount(budget, taxRate)
        maxTaxAmount.doubleValue = calculateMaxItemTax(maxItemPrice.doubleValue, taxRate)
    }

    fun calculateMaxItemAmount(budget: Double, taxRate: Double): Double {
        val taxMultiplier = 1 + (taxRate / 100.0)
        return budget / taxMultiplier
    }

    fun calculateMaxItemTax(maxItemAmount: Double, taxRate: Double): Double {
        return maxItemAmount * (taxRate / 100.0)
    }

    fun calculateProvincialBudget(province: Province) {
        val amount = enteredBudget.value.toDoubleOrNull() ?: 0.00
        gstAmount.doubleValue = maxItemPrice.doubleValue * (province.gst / 100.0)
        pstAmount.doubleValue = maxItemPrice.doubleValue * (province.pst / 100.0)
        hstAmount.doubleValue = maxItemPrice.doubleValue * (province.hst / 100.0)
        provMaxBudgetWithoutTax.doubleValue =
            (amount) - (pstAmount.doubleValue + gstAmount.doubleValue + hstAmount.doubleValue)
        println("gst: ${gstAmount.doubleValue}, item: ${provMaxBudgetWithoutTax.doubleValue}")
    }

    fun calculateStateTaxes(state: USState) {
        statesTaxAmount.doubleValue = maxItemPrice.doubleValue * (state.taxRate / 100.0)
        statesTotalAmountWithoutTax.doubleValue =
            (enteredBudget.value.toDoubleOrNull() ?: 0.0) - statesTaxAmount.doubleValue
    }
}