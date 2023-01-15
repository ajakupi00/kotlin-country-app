package jakupi.arjan.country

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jakupi.arjan.country.adapter.CountryPagerAdapter
import jakupi.arjan.country.databinding.ActivityCountryPagerBinding
import jakupi.arjan.country.framework.fetchCountries
import jakupi.arjan.country.model.Country

const val POSITION = "jakupi.arjan.country.position"

class CountryPagerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCountryPagerBinding
    private lateinit var countries: MutableList<Country>
    private var countryPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountryPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initPager()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun initPager() {
        countries = fetchCountries()
        countryPosition = intent.getIntExtra(POSITION, countryPosition)
        binding.viewPager.adapter = CountryPagerAdapter(this, countries)
        binding.viewPager.currentItem = countryPosition
    }
}