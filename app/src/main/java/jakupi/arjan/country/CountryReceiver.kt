package jakupi.arjan.country

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import jakupi.arjan.country.framework.setBooleanPreference
import jakupi.arjan.country.framework.startActivity

class CountryReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        context.setBooleanPreference(DATA_IMPORTED)
        context.startActivity<HostActivity>()
    }
}