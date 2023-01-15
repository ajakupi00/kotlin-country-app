package jakupi.arjan.country

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import jakupi.arjan.country.adapter.CountryAdapter
import jakupi.arjan.country.databinding.FragmentCountriesBinding
import jakupi.arjan.country.framework.fetchCountries
import jakupi.arjan.country.model.Country

class CountriesFragment : Fragment() {
    private lateinit var binding: FragmentCountriesBinding
    private lateinit var countries: MutableList<Country>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        countries = requireContext().fetchCountries()
        binding = FragmentCountriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvCountries.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = CountryAdapter(requireContext(), countries)
        }
    }


}