package salestaxcalculator.example.bcsalestax.ui.screens

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import salestaxcalculator.example.bcsalestax.data.Province

class MainViewModel : ViewModel() {
    val enterItemPrice = mutableStateOf("")
    val enterTax = mutableStateOf("")
    val totalBudget = mutableStateOf("")
    val taxAmount  = mutableStateOf(0.00)
    val totalAmount = mutableStateOf(0.00)
    val selectedOptions = "Custom Tax"

    val radioOptions = listOf("Custom Tax", "Provincial Tax")

    /**
     * This function calculates the tax amount and total amount based on the entered item price and tax rate.
     *
     * @param enterItemPrice: The price of the item entered by the user, as a string
     * @param enterTax: The tax rate entered by the user, as a string
     * @param taxAmount: A MutableState variable that holds the calculated tax amount, as a double
     * @param totalAmount: A MutableState variable that holds the calculated total amount, as a double
     *
     * @return No return value, it updates the taxAmount and totalAmount state variables
     *
     * Example:
     * enterItemPrice.value = "100"
     * enterTax.value = "10"
     * calculateAmounts()
     * taxAmount.value = 10.00
     * totalAmount.value = 110.00
     *
     * @throws IllegalArgumentException if the enterItemPrice or enterTax is not a valid double
     */
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

    /**
     * Calculates the PST (Provincial Sales Tax) for a given amount and province.
     * @param amount the total amount for which to calculate PST
     * @param province the Province object from which to retrieve the PST rate
     * @return the calculated PST for the given amount and province
     */
    private fun calculatePST(
        amount: Double,
        province: Province,
    ): Double {
        return province.PST * amount
    }

    /**
     * Calculates the GST of a given amount based on the GST rate of a Province object
     *
     * @param amount: The amount to calculate the GST on
     * @param province: Province object that contains the GST rate
     *
     * @return The calculated GST amount as a double
     */
    private fun calculateGST(
        amount: Double,
        province: Province,
    ): Double {
        return province.GST * amount
    }

    /**

    This function calculates the Harmonized Sales Tax (HST) amount for a given total amount and a Province object
    @param amount the total amount before taxes are applied
    @param hst a Province object which contains the HST rate as a property
    @return the calculated HST amount as a Double
     */
    private fun calculateHST(
        amount: Double,
        hst: Province,
    ): Double {
        return hst.GST * amount
    }

}
