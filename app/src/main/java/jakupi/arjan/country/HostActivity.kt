package jakupi.arjan.country

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import jakupi.arjan.country.databinding.ActivityMainBinding

class HostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}