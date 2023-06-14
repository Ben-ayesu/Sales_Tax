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
    fun testCalculateMaxItemAmount() {
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
}