package jakupi.arjan.country

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.lottie.LottieDrawable
import jakupi.arjan.country.databinding.ActivitySplashScreenBinding
import jakupi.arjan.country.databinding.FragmentAboutBinding


class AboutFragment : Fragment() {
    private lateinit var binding: FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAboutBinding.inflate(layoutInflater)

        binding.animationView.setAnimation(R.raw.arci)
        binding.animationView.repeatCount = LottieDrawable.INFINITE
        binding.animationView.playAnimation()

        return binding.root
    }


}