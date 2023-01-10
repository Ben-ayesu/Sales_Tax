package salestaxcalculator.example.bcsalestax.data

data class Location(
    val list: List<Province>
) {
    val provinces = listOf(
        Province("AB", 0, 0),
        Province("BC", 7, 0),
        Province("MB", 7, 0),
        Province("NB", 0, 15),
        Province("NL", 0, 15),
        Province("NT", 0, 0),
        Province("NS", 0, 15),
        Province("NU", 0, 0),
        Province("ON", 0, 13),
        Province("PE", 0, 15),
        Province("QC", 10, 0),
        Province("SK", 6, 0),
        Province("YT", 0, 0),
    )
}