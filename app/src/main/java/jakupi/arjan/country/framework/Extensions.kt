package jakupi.arjan.country.framework

import android.annotation.SuppressLint
import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.content.getSystemService
import androidx.preference.PreferenceManager
import jakupi.arjan.country.COUNTRY_PROVIDER_CONTENT_URI
import jakupi.arjan.country.model.Country

//import hr.algebra.nasa.NASA_PROVIDER_CONTENT_URI
//import hr.algebra.nasa.NasaReceiver
//import hr.algebra.nasa.model.Item

fun View.applyAnimation(animationId: Int)
        = startAnimation(AnimationUtils.loadAnimation(context, animationId))


inline fun<reified T: Activity> Context.startActivity()
        = startActivity(
    Intent(this, T::class.java)
        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))

inline fun<reified T: Activity> Context.startActivity(key: String, value: Int)
        = startActivity(
    Intent(this, T::class.java).apply {
        putExtra(key, value)
    }.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))

inline fun<reified T: BroadcastReceiver> Context.sendBroadcast()
        = sendBroadcast(Intent(this, T::class.java))

fun Context.setBooleanPreference(key: String, value: Boolean = true) {
    PreferenceManager.getDefaultSharedPreferences(this)
        .edit()
        .putBoolean(key, value)
        .apply()
}

fun Context.getBooleanPreference(key: String) =
    PreferenceManager.getDefaultSharedPreferences(this)
        .getBoolean(key, false)

fun Context.isOnline() : Boolean{
    val connectivityManager = getSystemService<ConnectivityManager>()
    connectivityManager?.activeNetwork?.let { network ->
        connectivityManager.getNetworkCapabilities(network)?.let { cap ->
            return cap.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                    || cap.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
        }
    }
    return false
}


fun callDelayed(delay: Long, runnable: Runnable) {
    Handler(Looper.getMainLooper()).postDelayed(
        runnable,
        delay
    )
}


@SuppressLint("Range")
fun Context.fetchCountries(selection: String?) : MutableList<Country> {
    val countries = mutableListOf<Country>()
    val cursor = contentResolver.query(
        COUNTRY_PROVIDER_CONTENT_URI,
        null, selection, null, null)
    while (cursor != null && cursor.moveToNext()) {
        countries.add(
            Country(
            cursor.getLong(cursor.getColumnIndex(Country::_id.name)),
            cursor.getString(cursor.getColumnIndex(Country::name.name)),
            cursor.getString(cursor.getColumnIndex(Country::capital.name)),
            cursor.getInt(cursor.getColumnIndex(Country::population.name)),
                cursor.getString(cursor.getColumnIndex(Country::timezone.name)),
                cursor.getString(cursor.getColumnIndex(Country::continents.name)),
                cursor.getString(cursor.getColumnIndex(Country::flagPath.name)),
                cursor.getInt(cursor.getColumnIndex(Country::favorite.name)) == 1
        ))
    }

    return countries
}
