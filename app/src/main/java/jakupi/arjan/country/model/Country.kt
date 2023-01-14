package jakupi.arjan.country.model

data class Country(
    val _id: Long?,
    val name: String,
    val capital: String,
    val population: Int,
    val currencies: String,
    val timezone: String,
    val continents: String,
    val flagPath: String,
    val favorite: Boolean
)
