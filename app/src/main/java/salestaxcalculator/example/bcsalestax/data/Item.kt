package salestaxcalculator.example.bcsalestax.data

data class Item(
    var id: Int,
    val priceIncludingTax: Double,
    val tax: Double
)
