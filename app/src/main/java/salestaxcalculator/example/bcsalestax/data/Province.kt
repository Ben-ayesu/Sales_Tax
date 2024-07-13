package salestaxcalculator.example.bcsalestax.data

data class Province(
    val name: String,
    val pst: Double,
    val hst: Double,
    val gst: Double = if (hst == 0.0) 5.0 else 0.0 // GST is included in HST
) {
    override fun toString() = name
}

val provinces = listOf(
    Province("Alberta", pst = 0.0, hst = 0.0),
    Province("British Columbia", pst = 7.0, hst = 0.0),
    Province("Manitoba", pst = 7.0, hst = 0.0),
    Province("New Brunswick", pst = 0.0, hst = 15.0),
    Province("Newfoundland and Labrador", pst = 0.0, hst = 15.0),
    Province("Northwest Territories", pst = 0.0, hst = 0.0),
    Province("Nova Scotia", pst = 0.0, hst = 15.0),
    Province("Nunavut", pst = 0.0, hst = 0.0),
    Province("Ontario", pst = 0.0, hst = 13.0),
    Province("Prince Edward Island", pst = 0.0, hst = 15.0),
    Province("Quebec", pst = 9.975, hst = 0.0),
    Province("Saskatchewan", pst = 6.0, hst = 0.0),
    Province("Yukon", pst = 0.0, hst = 0.0)
)
