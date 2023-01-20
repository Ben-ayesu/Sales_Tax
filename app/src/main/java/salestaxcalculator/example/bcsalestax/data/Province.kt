package salestaxcalculator.example.bcsalestax.data
data class Province(
    var provinceName: String,
    var PST: Int,
    var HST: Int,
    var GST: Int = 5
)

val provinces = listOf(
    Province("Alberta", 0, 0),
    Province("British Columbia", 7, 0),
    Province("Manitoba", 7, 0),
    Province("New Brunswick", 0, 15,0),
    Province("NewFoundLand and Labrador", 0, 15,0),
    Province("Northwest Territories", 0, 0),
    Province("Nova Scotia", 0, 15,0),
    Province("Nunavut", 0, 0),
    Province("Ontario", 0, 13),
    Province("Prince Edward Island", 0, 15,0),
    Province("Quebec", 10, 0),
    Province("Saskatoon", 6, 0),
    Province("Yukon", 0, 0),
)
