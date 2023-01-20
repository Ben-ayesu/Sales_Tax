package com.example.bcsalestax

import org.junit.Test

import org.junit.Assert.*
import salestaxcalculator.example.bcsalestax.ui.screens.MainViewModel

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MainViewModelTest {

    private val viewModel = MainViewModel()

    @Test
    fun `calculateAmounts sets correct tax and total amounts`() {
        viewModel.enterItemPrice.value = "100"
        viewModel.enterTax.value = "10"
        viewModel.calculateAmounts()

        assertEquals(10.00, viewModel.taxAmount.value, 0.001)
        assertEquals(110.00, viewModel.totalAmount.value, 0.001)
    }

    @Test
    fun `calculateAmounts sets 0 as default value for tax and total amounts if inputs are invalid`() {
        viewModel.enterItemPrice.value = "invalid"
        viewModel.enterTax.value = "invalid"
        viewModel.calculateAmounts()

        assertEquals(0.00, viewModel.taxAmount.value, 0.001)
        assertEquals(0.00, viewModel.totalAmount.value, 0.001)
    }

}