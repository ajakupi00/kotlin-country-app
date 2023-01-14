package jakupi.arjan.country.api

import android.content.ContentValues
import android.content.Context
import android.util.Log
import jakupi.arjan.country.COUNTRY_PROVIDER_CONTENT_URI
import jakupi.arjan.country.handler.downloadImageAndStore
import jakupi.arjan.country.model.Country
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.ArrayList

class CountryFetcher(private val context: Context) {
    private var countryApi: CountryApi

    init{
        val retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        countryApi = retrofit.create(CountryApi::class.java)
    }

    fun fetchItems(count: Int){
        val request = countryApi.fetchItems(count)

        request.enqueue(object: Callback<List<CountryItem>> {
            override fun onResponse(
                call: Call<List<CountryItem>>,
                response: Response<List<CountryItem>>
            ) {
                response?.body()?.let {  populateItems(it) }
            }

            override fun onFailure(call: Call<List<CountryItem>>, t: Throwable) {
                Log.e(javaClass.name, t.toString(), t)
            }
        })
    }

    private fun populateItems(countryItems: List<CountryItem>){
        GlobalScope.launch {
            countryItems.forEach {
                var flagPath = downloadImageAndStore(context, it.flags.svg)
                val values = ContentValues().apply {
                    put(Country::name.name, it.name.official)
                    put(Country::capital.name, it.capital[0])
                    put(Country::population.name, it.population)
                    put(Country::flagPath.name, flagPath)
                    put(Country::currencies.name, it.currencies.XCD.name)
                    put(Country::timezone.name, concatStringArray(it.timezones))
                    put(Country::continents.name, concatStringArray(it.continents))
                    put(Country::favorite.name, false)
                }
                context.contentResolver.insert(COUNTRY_PROVIDER_CONTENT_URI, values)
            }
        }
    }

    private fun concatStringArray(stringArray: ArrayList<String>): String? {
        val sb = StringBuilder();
        stringArray.forEach { string ->
            sb.append(string + ",")
        }
        return sb.toString()
    }
}