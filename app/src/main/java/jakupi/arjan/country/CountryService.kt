package jakupi.arjan.country

import android.content.Context
import android.content.Intent
import androidx.core.app.JobIntentService

private const val JOB_ID = 1
@Suppress("DEPRECATION")
class CountryService : JobIntentService() {

    override fun onHandleWork(intent: Intent) {
        Thread.sleep(3000L)
    }

    companion object {
        fun enqueue(context: Context){
            enqueueWork(context, CountryService::class.java, JOB_ID,
                                Intent(context, CountryService::class.java))
        }
    }

}