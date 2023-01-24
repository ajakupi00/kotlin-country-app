package jakupi.arjan.country.api

import com.google.gson.annotations.SerializedName


data class CountryItem (

    @SerializedName("name"         ) var name         : Name,
    @SerializedName("capital"      ) var capital      : ArrayList<String>,
    @SerializedName("population"   ) var population   : Int,
    @SerializedName("timezones"    ) var timezones    : ArrayList<String>,
    @SerializedName("continents"   ) var continents   : ArrayList<String>,
    @SerializedName("flags"        ) var flags        : Flags,
    @SerializedName("latlng"       ) var latlng       : ArrayList<Double>,
    )

data class Name(
    @SerializedName("common"     ) var common     : String?     = null,
    @SerializedName("official"   ) var official   : String?     = null,
)

data class Flags (
    @SerializedName("png" ) var svg : String

)

