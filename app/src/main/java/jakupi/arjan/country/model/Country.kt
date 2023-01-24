package jakupi.arjan.country.model

data class Country(
    val _id: Long?,
    val name: String,
    val capital: String,
    val population: Int,
    val timezone: String,
    val continents: String,
    val flagPath: String,
    val latlng: String,
    var favorite: Boolean
)
