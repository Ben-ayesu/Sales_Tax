package salestaxcalculator.example.bcsalestax.data

data class State(
    var stateName: String,
    var taxRate: Double
) {
    override fun toString(): String {
        return stateName
    }
}

val states = listOf(
    State("Alabama", 4.0),
    State("Alaska", 0.0),
    State("Arizona", 5.6),
    State("Arkansas", 6.5),
    State("California", 7.25),
    State("Colorado", 2.9),
    State("Connecticut", 6.35),
    State("Delaware", 0.0),
    State("Florida", 6.0),
    State("Georgia", 4.0),
    State("Hawaii", 4.0),
    State("Idaho", 6.0),
    State("Illinois", 6.25),
    State("Indiana", 7.0),
    State("Iowa", 6.0),
    State("Kansas", 6.5),
    State("Kentucky", 6.0),
    State("Louisiana", 4.45),
    State("Maine", 5.5),
    State("Maryland", 6.0),
    State("Massachusetts", 6.25),
    State("Michigan", 6.0),
    State("Minnesota", 6.875),
    State("Mississippi", 7.0),
    State("Missouri", 4.225),
    State("Montana", 0.0),
    State("Nebraska", 5.5),
    State("Nevada", 6.85),
    State("New Hampshire", 0.0),
    State("New Jersey", 6.625),
    State("New Mexico", 5.125),
    State("New York", 4.0),
    State("North Carolina", 4.75),
    State("North Dakota", 5.0),
    State("Ohio", 5.75),
    State("Oklahoma", 4.5),
    State("Oregon", 0.0),
    State("Pennsylvania", 6.0),
    State("Rhode Island", 7.0),
    State("South Carolina", 6.0),
    State("South Dakota", 4.5),
    State("Tennessee", 7.0),
    State("Texas", 6.25),
    State("Utah", 4.7),
    State("Vermont", 6.0),
    State("Virginia", 5.3),
    State("Washington", 6.5),
    State("West Virginia", 6.0),
    State("Wisconsin", 5.0),
    State("Wyoming", 4.0)
)