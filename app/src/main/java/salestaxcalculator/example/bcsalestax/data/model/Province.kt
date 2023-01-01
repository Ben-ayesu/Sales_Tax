package salestaxcalculator.example.bcsalestax.data.model

data class Province(
    var provinceName: String,
    var provincialTax: Int,
    var harmonizedTax: Int,
    var federalTax: Int = 5
) {
}
