package salestaxcalculator.example.bcsalestax.data

data class USState(
    var stateName: String,
    var taxRate: Double
) {
    override fun toString(): String {
        return stateName
    }
}

val USStates = listOf(
    USState("Alabama", 4.0),
    USState("Alaska", 0.0),
    USState("Arizona", 5.6),
    USState("Arkansas", 6.5),
    USState("California", 7.25),
    USState("Colorado", 2.9),
    USState("Connecticut", 6.35),
    USState("Delaware", 0.0),
    USState("Florida", 6.0),
    USState("Georgia", 4.0),
    USState("Hawaii", 4.0),
    USState("Idaho", 6.0),
    USState("Illinois", 6.25),
    USState("Indiana", 7.0),
    USState("Iowa", 6.0),
    USState("Kansas", 6.5),
    USState("Kentucky", 6.0),
    USState("Louisiana", 4.45),
    USState("Maine", 5.5),
    USState("Maryland", 6.0),
    USState("Massachusetts", 6.25),
    USState("Michigan", 6.0),
    USState("Minnesota", 6.875),
    USState("Mississippi", 7.0),
    USState("Missouri", 4.225),
    USState("Montana", 0.0),
    USState("Nebraska", 5.5),
    USState("Nevada", 6.85),
    USState("New Hampshire", 0.0),
    USState("New Jersey", 6.625),
    USState("New Mexico", 5.125),
    USState("New York", 4.0),
    USState("North Carolina", 4.75),
    USState("North Dakota", 5.0),
    USState("Ohio", 5.75),
    USState("Oklahoma", 4.5),
    USState("Oregon", 0.0),
    USState("Pennsylvania", 6.0),
    USState("Rhode Island", 7.0),
    USState("South Carolina", 6.0),
    USState("South Dakota", 4.5),
    USState("Tennessee", 7.0),
    USState("Texas", 6.25),
    USState("Utah", 4.7),
    USState("Vermont", 6.0),
    USState("Virginia", 5.3),
    USState("Washington", 6.5),
    USState("West Virginia", 6.0),
    USState("Wisconsin", 5.0),
    USState("Wyoming", 4.0)
)