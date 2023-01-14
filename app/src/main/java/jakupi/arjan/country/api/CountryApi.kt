package jakupi.arjan.country.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


const val API_URL = "https://restcountries.com/v3/"
interface CountryApi {
    @GET("all")
    fun fetchItems(@Query("count") count: Int) : Call<List<CountryItem>>

}