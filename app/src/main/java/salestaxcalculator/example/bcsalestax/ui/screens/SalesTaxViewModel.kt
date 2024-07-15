package salestaxcalculator.example.bcsalestax.ui.screens

import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import salestaxcalculator.example.bcsalestax.data.Item
import salestaxcalculator.example.bcsalestax.data.Province
import salestaxcalculator.example.bcsalestax.data.USState

class SalesTaxViewModel : ViewModel() {
    // Enter Item Price and Tax Rate
    val priceInput = mutableStateOf("")
    val taxInput = mutableStateOf("")

    // Amount to be updated on results view for custom tax and provincial tax
    val taxAmount = mutableDoubleStateOf(0.00)
    val taxRate = mutableDoubleStateOf(0.00)
    val totalAmount = mutableDoubleStateOf(0.00)

    // provincial tax calculation and view
    val pstAmount = mutableDoubleStateOf(0.00)
    val gstAmount = mutableDoubleStateOf(0.00)
    val hstAmount = mutableDoubleStateOf(0.00)
    val provTotalAmount = mutableDoubleStateOf(0.0)

    // state tax
    val statesTaxAmount = mutableDoubleStateOf(0.0)
    val statesTotalAmount = mutableDoubleStateOf(0.0)

    // List for the radio button options
    val radioOptions = listOf("🌎 Custom Tax", "🇨🇦 Canada", "🇺🇸 USA")

    // initial selected option
    val initialSelectedOption = radioOptions.first()
    val selectedOptionState = mutableStateOf(initialSelectedOption)

    val handleOptionSelected: (String) -> Unit = { selectedOption ->
        selectedOptionState.value = selectedOption
        // Update tax rate based on selected option
        when (selectedOption) {
            "🌎 Custom Tax" -> taxRate.doubleValue
            "🇨🇦 Canada" -> taxRate.doubleValue
            "🇺🇸 USA" -> taxRate.doubleValue
        }
    }

    // State for bottom sheet
    var bottomSheetState = false

    fun calculateAmounts() {
        val amount = priceInput.value.toDoubleOrNull() ?: 0.00
        val tax = taxInput.value.toDoubleOrNull() ?: 0.00
        taxAmount.doubleValue = calculateTotalTax(amount, tax)
        totalAmount.doubleValue = calculateTotalAmount(amount, taxAmount.doubleValue)
    }

    fun calculateProvincialTaxes(province: Province) {
        val amount = priceInput.value.toDoubleOrNull() ?: 0.00
        gstAmount.doubleValue = amount * (province.gst / 100.0)
        pstAmount.doubleValue = amount * (province.pst / 100.0)
        hstAmount.doubleValue = amount * (province.hst / 100.0)
        provTotalAmount.doubleValue = amount + gstAmount.doubleValue + pstAmount.doubleValue + hstAmount.doubleValue
    }

    fun calculateStateTaxes(state: USState) {
        val amount = priceInput.value.toDoubleOrNull() ?: 0.00
        taxRate.doubleValue = state.taxRate
        statesTaxAmount.doubleValue = amount * (state.taxRate / 100.0)
        statesTotalAmount.doubleValue = amount + statesTaxAmount.doubleValue
    }

    private fun calculateTotalTax(
        amount: Double,
        taxRate: Double
    ): Double {
        return taxRate / 100 * amount
    }

    private fun calculateTotalAmount(
        amount: Double,
        taxAmount: Double
    ): Double {
        return amount + taxAmount
    }

    // List of items
    var itemList = mutableStateListOf<Item>()
    var itemIndex = 1

    // Function for validating if user has fields full or tax rate is not between 0-100 before adding item
    fun validateInput(): Boolean {
        // Check if price or tax input is blank
        if (priceInput.value.isBlank() || taxInput.value.isBlank()) {
            return false
        }

        // Checks if price is positive
        val price = priceInput.value.toDoubleOrNull()
        if (price == null || price <= 0) {
            return false
        }

        // Validate tax rate is within 0-100
        val taxRate = taxInput.value.toDoubleOrNull()
        return !(taxRate == null || taxRate < 0 || taxRate > 100)
    }

    // Function for adding an item to a list
    fun addItem(item: Item) {
        item.id = itemIndex
        if (validateInput()) {
            item.id = itemIndex++
            itemList.add(item)
        }
    }

    // Function for removing an item from a list
    fun deleteItem(item: Item) {
        itemList.remove(item)

        // Handle empty list case
        if (itemList.isEmpty()) {
            itemIndex = 0
        }
    }
}
