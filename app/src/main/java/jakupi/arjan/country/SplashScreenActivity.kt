package jakupi.arjan.country

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.core.content.ContentProviderCompat.requireContext
import jakupi.arjan.country.api.CountryFetcher
import jakupi.arjan.country.databinding.ActivitySplashScreenBinding
import jakupi.arjan.country.framework.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


private const val DELAY = 3000L
const val DATA_IMPORTED = "jakupi.arjan.country.data_imported"

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progressBar.max = CountryFetcher.NUMBER_OF_COUNTRIES_TO_FETCH
        displayRandomFunFact()
        redirect()
    }

    private fun displayRandomFunFact() {
        binding.tvSplash.applyAnimation(R.anim.blink)
        binding.ivSplash.applyAnimation(R.anim.rotate)
    }


    private fun redirect() {
        if(getBooleanPreference(DATA_IMPORTED)){
            callDelayed(DELAY) {startProgressBar()}
        } else{
            if(isOnline()){
                GlobalScope.launch {
                    while(!getBooleanPreference(DATA_IMPORTED)){
                            binding.progressBar.setProgress(CountryFetcher.PROGRESS_BAR_INDEX, true)
                        }
                }
                CountryService.enqueue(this)
            }else {
                binding.tvSplash.text = "No internet"
                callDelayed(DELAY) {finish()}
            }
        }

    }

    private fun startProgressBar() {
        for (i in 0..50){
            binding.progressBar.setProgress(i, true)
        }
        startActivity<HostActivity>()
    }
}