package salestaxcalculator.example.bcsalestax.ui.Screens

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
    val taxAmount = mutableStateOf(0.00)
    val totalAmount = mutableStateOf(0.00)

    // provincial tax calculation and view
    val pstAmount = mutableStateOf(0.00)
    val gstAmount = mutableStateOf(0.00)
    val hstAmount = mutableStateOf(0.00)
    val provTotalAmount = mutableStateOf(0.0)

    // state tax
    val statesTaxAmount = mutableStateOf(0.0)
    val statesTotalAmount = mutableStateOf(0.0)

    // List for the radio button options
    val radioOptions = listOf("🌎 Custom Tax", "🇨🇦 Canada", "🇺🇸 USA")

    // initial selected option
    var selectedOptions = radioOptions.first()
    val selectedOptionState = mutableStateOf(selectedOptions)

    val onOptionSelected: (String) -> Unit = { selectedOption ->
        selectedOptionState.value = selectedOption
    }

    // State for bottom sheet
    var bottomSheetState = false

    fun calculateAmounts() {
        val amount = priceInput.value.toDoubleOrNull() ?: 0.00
        val tax = taxInput.value.toDoubleOrNull() ?: 0.00
        taxAmount.value = calculateTotalTax(amount, tax)
        totalAmount.value = calculateTotalAmount(amount, taxAmount.value)
    }

    fun calculateProvincialTaxes(province: Province) {
        val amount = priceInput.value.toDoubleOrNull() ?: 0.00
        gstAmount.value = amount * (province.GST / 100.0)
        pstAmount.value = amount * (province.PST / 100.0)
        hstAmount.value = amount * (province.HST / 100.0)
        provTotalAmount.value = amount + gstAmount.value + pstAmount.value + hstAmount.value
    }

    fun calculateStateTaxes(state: USState) {
        val amount = priceInput.value.toDoubleOrNull() ?: 0.00
        statesTaxAmount.value = amount * (state.taxRate / 100.0)
        statesTotalAmount.value = amount + statesTaxAmount.value
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

        val taxRate = taxInput.value.toDoubleOrNull()

        // Check if tax rate is invalid
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
