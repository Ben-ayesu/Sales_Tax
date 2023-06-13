package com.example.bcsalestax

import org.junit.Assert.assertEquals
import org.junit.Test
import salestaxcalculator.example.bcsalestax.ui.Screens.BudgetViewModel
import salestaxcalculator.example.bcsalestax.ui.Screens.SalesTaxViewModel

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class SalesTaxViewModelTest {

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
    fun calculateBudget_withValidInputs_updatesMaxItemAmountAndMaxTaxAmount() {
        // Arrange
        budgetViewModel.enterBudget.value = "20"
        budgetViewModel.enterTax.value = "10"
        val expectedMaxItemAmount = 18.18
        val expectedMaxTaxAmount = 1.82

        // Act
        budgetViewModel.calculateBudget()

        // Assert
        assertEquals(expectedMaxItemAmount, budgetViewModel.maxItemAmount.value, 0.01)
        assertEquals(expectedMaxTaxAmount, budgetViewModel.maxTaxAmount.value, 0.01)
    }

    @Test
    fun calculateBudget_withInvalidInputs_updatesMaxItemAmountAndMaxTaxAmountToZero() {
        // Arrange
        budgetViewModel.enterBudget.value = "invalid"
        budgetViewModel.enterTax.value = "invalid"
        val expectedMaxItemAmount = 0.0
        val expectedMaxTaxAmount = 0.0

        // Act
        budgetViewModel.calculateBudget()

        // Assert
        assertEquals(expectedMaxItemAmount, budgetViewModel.maxItemAmount.value, 0.01)
        assertEquals(expectedMaxTaxAmount, budgetViewModel.maxTaxAmount.value, 0.01)
    }

    @Test
    fun calculateBudget_withZeroBudget_updatesMaxItemAmountToZero() {
        // Arrange
        budgetViewModel.enterBudget.value = "0"
        budgetViewModel.enterTax.value = "10"
        val expectedMaxItemAmount = 0.0

        // Act
        budgetViewModel.calculateBudget()

        // Assert
        assertEquals(expectedMaxItemAmount, budgetViewModel.maxItemAmount.value, 0.01)
    }

    
}