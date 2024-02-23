package salestaxcalculator.example.bcsalestax.data
data class Province(
    var provinceName: String,
    var pst: Double,
    var hst: Double,
    var gst: Double = 5.0 // Note: In provinces with HST, GST is effectively part of HST
) {
    override fun toString(): String {
        return provinceName
    }
}

val provinces = listOf(
    Province("Alberta", 0.0, 0.0),
    Province("British Columbia", 7.0, 0.0),
    Province("Manitoba", 7.0, 0.0),
    Province("New Brunswick", 0.0, 15.0, 0.0),
    Province("NewFoundLand and Labrador", 0.0, 15.0, 0.0),
    Province("Northwest Territories", 0.0, 0.0),
    Province("Nova Scotia", 0.0, 15.0, 0.0),
    Province("Nunavut", 0.0, 0.0),
    Province("Ontario", 0.0, 13.0, 0.0),
    Province("Prince Edward Island", 0.0, 15.0, 0.0),
    Province("Quebec", 9.975, 0.0),
    Province("Saskatchewan", 6.0, 0.0),
    Province("Yukon", 0.0, 0.0),
)