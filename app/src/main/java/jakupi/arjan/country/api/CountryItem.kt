package jakupi.arjan.country.api

import com.google.gson.annotations.SerializedName


data class CountryItem (

    @SerializedName("name"         ) var name         : Name,
    @SerializedName("currencies"   ) var currencies   : Currencies,
    @SerializedName("capital"      ) var capital      : ArrayList<String>,
    @SerializedName("population"   ) var population   : Int,
    @SerializedName("timezones"    ) var timezones    : ArrayList<String>,
    @SerializedName("continents"   ) var continents   : ArrayList<String>,
    @SerializedName("flags"        ) var flags        : Flags,

)

data class Name(
    @SerializedName("common"     ) var common     : String?     = null,
    @SerializedName("official"   ) var official   : String?     = null,
)

data class Currencies (
    @SerializedName("XCD" ) var XCD : XCD
)

data class XCD (
    @SerializedName("name"   ) var name   : String
)

data class Flags (
    @SerializedName("svg" ) var svg : String

)

