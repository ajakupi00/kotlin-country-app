package jakupi.arjan.country

import android.content.Context
import android.content.Intent
import androidx.core.app.JobIntentService
import jakupi.arjan.country.api.CountryFetcher

private const val JOB_ID = 1
@Suppress("DEPRECATION")
class CountryService : JobIntentService() {

    override fun onHandleWork(intent: Intent) {
        CountryFetcher(this).fetchItems()
    }

    companion object {
        fun enqueue(context: Context){
            enqueueWork(context, CountryService::class.java, JOB_ID,
                                Intent(context, CountryService::class.java))
        }
    }

}