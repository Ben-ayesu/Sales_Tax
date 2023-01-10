package salestaxcalculator.example.bcsalestax.ui.screens

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val enterItemPrice = mutableStateOf("")
    val enterTax = mutableStateOf("")
    val totalBudget = mutableStateOf("")
    val taxAmount  = mutableStateOf(0.00)
    val totalAmount = mutableStateOf(0.00)

    fun calculateAmounts() {
        val amount = enterItemPrice.value?.toDoubleOrNull() ?: 0.00
        val tax = enterTax.value?.toDoubleOrNull() ?: 0.00
        taxAmount.value = calculateTaxesTotal(amount, tax)
        totalAmount.value = calculateTotalAmount(amount, taxAmount.value)
    }


    /**
     * Calculates the total amount of taxes for a given amount and tax rate.
     *
     * @param amount a `Double` representing the amount to be taxed
     * @param taxRate a `Double` representing the tax rate as a percentage (e.g., a value of `20.0` represents a tax rate of 20%)
     * @return a `Double` representing the total amount of taxes calculated based on the given amount and tax rate
     */
    private fun calculateTaxesTotal(
        amount: Double,
        taxRate: Double
    ): Double {
        return taxRate / 100 * amount
    }

    /**
     * Calculates the total amount including tax for a given amount and tax amount.
     *
     * @param amount a `Double` representing the amount to be taxed
     * @param taxAmount a `Double` representing the tax amount
     * @return a `Double` representing the total amount including tax calculated based on the given amount and tax amount
     */
    private fun calculateTotalAmount(
        amount: Double,
        taxAmount: Double
    ): Double {
        return amount + taxAmount
    }

    /**
     * Calculates the item price based on the total amount including tax.
     *
     * @param amount a `Double` representing the total amount including tax
     * @param taxRate a `Double` representing the tax rate as a percentage (e.g., a value of `20.0` represents a tax rate of 20%)
     * @return a `Double` representing the item price calculated based on the given total amount and tax rate
     */
    private fun calculateItemPriceBasedOnTotal(
        amount: Double,
        taxRate: Double
    ): Double {
        return amount / 1 + taxRate
    }
}
