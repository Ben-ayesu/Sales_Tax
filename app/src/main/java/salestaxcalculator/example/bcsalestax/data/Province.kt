package salestaxcalculator.example.bcsalestax.data

data class Province(
    var provinceName: String,
    var PST: Int,
    var HST: Int,
    var GST: Int = 5
) {
}
