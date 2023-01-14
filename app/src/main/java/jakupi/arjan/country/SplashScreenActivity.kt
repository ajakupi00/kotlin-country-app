package jakupi.arjan.country

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jakupi.arjan.country.databinding.ActivitySplashScreenBinding
import jakupi.arjan.country.framework.callDelayed
import jakupi.arjan.country.framework.getBooleanPreference
import jakupi.arjan.country.framework.isOnline
import jakupi.arjan.country.framework.startActivity


private const val DELAY = 3000L
const val DATA_IMPORTED = "jakupi.arjan.country.data_imported"

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //displayRandomFunFact()
        redirect()
    }

    private fun displayRandomFunFact() {
        // BIND FUN FACT
        // APPLY ANIMATIONS
    }

    private fun redirect() {
        if(getBooleanPreference(DATA_IMPORTED)){
            callDelayed(DELAY) {startActivity<HostActivity>()}
        } else{
            if(isOnline()){
                CountryService.enqueue(this)
            }else {
                binding.tvSplash.text = "No internet"
                callDelayed(DELAY) {finish()}
            }
        }

    }
}