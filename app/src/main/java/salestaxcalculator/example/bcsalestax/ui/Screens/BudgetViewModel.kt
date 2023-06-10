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
    var maxTaxamount = mutableStateOf(0.0)

    // provincial tax calculation and view
    val pstAmount = mutableStateOf(0.00)
    val gstAmount = mutableStateOf(0.00)
    val hstAmount = mutableStateOf(0.00)
    val provTotalAmount = mutableStateOf(0.0)

    // state tax
    val statesTaxAmount = mutableStateOf(0.0)
    val statesTotalAmount = mutableStateOf(0.0)

    // List for the radio button options
    val radioOptions = listOf("Custom Tax", "Canada", "United States")

    // initial selected option
    val selectedOptions = radioOptions.first()

    fun calculateBudget() {
        maxItemAmount.value = calculateMaxItemAmount(
            enterBudget.value.toDoubleOrNull() ?: 0.00,
            enterTax.value.toDoubleOrNull() ?: 0.00
        )
        maxTaxamount.value =
            calculateMaxTaxItem()
    }

    // Calculates item price based on budget and tax entered
    private fun calculateMaxItemAmount(
        budget: Double,
        taxAmount: Double
    ): Double {
        return (budget / (1 + (taxAmount / 100)))
    }

    private fun calculateMaxTaxItem() =
        enterBudget.value.toDoubleOrNull() ?: (0.00 - maxItemAmount.value)

    fun calculateProvincialTaxes(province: Province) {
        val amount = enterBudget.value?.toDoubleOrNull() ?: 0.00
        gstAmount.value = amount * (province.GST / 100.0)
        pstAmount.value = amount * (province.PST / 100.0)
        hstAmount.value = amount * (province.HST / 100.0)
        provTotalAmount.value = amount + gstAmount.value + pstAmount.value + hstAmount.value
    }

    fun calculateStateTaxes(state: USState) {
        val amount = enterBudget.value?.toDoubleOrNull() ?: 0.00
        statesTaxAmount.value = amount * (state.taxRate / 100.0)
        statesTotalAmount.value = amount + statesTaxAmount.value
    }
}