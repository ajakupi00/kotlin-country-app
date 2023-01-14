package jakupi.arjan.country

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jakupi.arjan.country.databinding.ActivitySplashScreenBinding
import jakupi.arjan.country.framework.startActivity


private const val DELAY = 3000L

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        redirect()
    }

    private fun redirect() {
        Thread.sleep(DELAY)
        startActivity<HostActivity>()
    }
}