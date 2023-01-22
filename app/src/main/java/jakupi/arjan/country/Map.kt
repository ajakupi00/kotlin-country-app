package jakupi.arjan.country

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import jakupi.arjan.country.databinding.ActivityMapBinding


class Map : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var mapFragment = getSupportFragmentManager().findFragmentById(R.id.maps) as SupportMapFragment
        mapFragment.getMapAsync(this)

        initNavigation()

    }

    override fun onMapReady(googleMap: GoogleMap) {
        val zagreb = LatLng(45.815399, 15.966568)
        googleMap.addMarker(
            MarkerOptions()
                .position(zagreb)
                .title("Zagreb")
        )
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(zagreb))

        val ljubljana = LatLng(46.056946,14.505752);
        googleMap.addMarker(
            MarkerOptions()
                .position(ljubljana)
                .title("Ljubljana")
        )

       googleMap.uiSettings.isZoomControlsEnabled = true
    }

    private fun initNavigation() {
        val navController = Navigation.findNavController(this, R.id.navController)
        NavigationUI.setupWithNavController(binding.navView, navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.host_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menuExit -> {
                exitApp()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun exitApp() {
        finish()
    }


}