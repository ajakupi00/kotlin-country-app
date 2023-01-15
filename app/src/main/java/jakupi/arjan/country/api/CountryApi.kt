package jakupi.arjan.country.api

import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.ArrayList


const val API_URL = "https://restcountries.com/v3.1/"
interface CountryApi {
    @GET("all")
    fun fetchItems() : Call<List<CountryItem>>

}