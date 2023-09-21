package com.example.bcsalestax

import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test
import salestaxcalculator.example.bcsalestax.ui.Screens.BudgetViewModel
import salestaxcalculator.example.bcsalestax.ui.Screens.SalesTaxViewModel

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ViewmodelLogic {

    private val salesViewModel = SalesTaxViewModel()
    private val budgetViewModel = BudgetViewModel()

    @Test
    fun `calculateAmounts sets correct tax and total amounts`() {
        salesViewModel.enterItemPrice.value = "100"
        salesViewModel.enterTax.value = "10"
        salesViewModel.calculateAmounts()

        assertEquals(10.00, salesViewModel.taxAmount.value, 0.001)
        assertEquals(110.00, salesViewModel.totalAmount.value, 0.001)
    }

    @Test
    fun `calculateAmounts sets 0 as default value for tax and total amounts if inputs are invalid`() {
        salesViewModel.enterItemPrice.value = "invalid"
        salesViewModel.enterTax.value = "invalid"
        salesViewModel.calculateAmounts()

        assertEquals(0.00, salesViewModel.taxAmount.value, 0.001)
        assertEquals(0.00, salesViewModel.totalAmount.value, 0.001)
    }

    @Test
    fun salesViewModel() {
        val budget = 100.0
        val taxRate = 10.0

        val result = budgetViewModel.calculateMaxItemAmount(budget, taxRate)

        assertEquals(90.0, result, 0.01)
    }

    @Test
    fun testCalculateMaxItemTax() {
        val maxItemAmount = 90.0
        val taxRate = 10.0

        val result = budgetViewModel.calculateMaxItemTax(maxItemAmount, taxRate)

        assertEquals(9.0, result, 0.001)
    }

    @Test
    fun calculateAmounts_withInvalidPrice_usesDefaultValue() {

        // Given
        salesViewModel.enterItemPrice.value = "invalid"
        salesViewModel.enterTax.value = "15.0"

        // When
        salesViewModel.calculateAmounts()

        // Then
        assertEquals(0.0, salesViewModel.taxAmount.value)
        assertEquals(0.0, salesViewModel.totalAmount.value)

    }

    @Test
    fun validateInput_withEmptyValues_returnsFalse() {

        // Given
        salesViewModel.enterItemPrice.value = ""
        salesViewModel.enterTax.value = ""

        // When
        val result = salesViewModel.validateInput()

        // Then
        Assert.assertFalse(result)

    }

    @Test
    fun calculateAmounts_withValidInputs_calculatesCorrectly() {

        // Given
        salesViewModel.enterItemPrice.value = "100.0"
        salesViewModel.enterTax.value = "10.0"

        // When
        salesViewModel.calculateAmounts()

        // Then
        val delta = 0.01
        assertEquals(10.0, salesViewModel.taxAmount.value, delta)
        assertEquals(110.0, salesViewModel.totalAmount.value, delta)

    }

    @Test
    fun calculateAmounts_withZeroPrice_returnsZero() {

        // Given
        salesViewModel.enterItemPrice.value = "0.0"
        salesViewModel.enterTax.value = "15.0"

        // When
        salesViewModel.calculateAmounts()

        // Then
        val delta = 0.01
        assertEquals(0.0, salesViewModel.taxAmount.value, delta)
        assertEquals(0.0, salesViewModel.totalAmount.value, delta)

    }
}