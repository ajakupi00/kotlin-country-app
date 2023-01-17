package jakupi.arjan.country.adapter

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import jakupi.arjan.country.COUNTRY_PROVIDER_CONTENT_URI
import jakupi.arjan.country.R
import jakupi.arjan.country.model.Country
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import java.io.File
private var NOTIFICATION_ID = "jakupi.arjan.country.notification"
class CountryPagerAdapter(private val context: Context, private val items: MutableList<Country>)
    : RecyclerView.Adapter<CountryPagerAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivItem = itemView.findViewById<ImageView>(R.id.ivFlag)
        val ivRead = itemView.findViewById<ImageView>(R.id.ivFavorite)

        private val tvName = itemView.findViewById<TextView>(R.id.tvName)
        private val tvCapital = itemView.findViewById<TextView>(R.id.tvCapital)
        private val tvPopulation = itemView.findViewById<TextView>(R.id.tvPopulation)
        private val tvContinent = itemView.findViewById<TextView>(R.id.tvContinent)
        private val tvTimezone = itemView.findViewById<TextView>(R.id.tvTimeZone)

        fun bind(item: Country) {
            Picasso.get()
                .load(File(item.flagPath))
                .error(R.drawable.robot_ballon)
                .transform(RoundedCornersTransformation(50, 5))
                .into(ivItem)
            tvName.text = item.name
            tvCapital.text = item.capital
            tvContinent.text = item.continents
            tvTimezone.text = item.timezone
            tvPopulation.text = item.population.toString()
            ivRead.setImageResource(if(item.favorite) R.drawable.heart_fillpng else R.drawable.heart_nofill)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            var channel = NotificationChannel(NOTIFICATION_ID, NOTIFICATION_ID, NotificationManager.IMPORTANCE_DEFAULT)
            var manager = getSystemService(context, NotificationManager::class.java)
            manager!!.createNotificationChannel(channel)
        }
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.country_pager, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.ivRead.setOnClickListener {
            // update
            var builder = NotificationCompat.Builder(context, NOTIFICATION_ID)
            item.favorite = !item.favorite


            builder.setContentTitle(item.name + " updated!");
            if(item.favorite){
                builder.setContentText(item.name + " is now one of your favorite countries");
            }else {
                builder.setContentText(item.name + " is removed from your favorite countries");
            }

            builder.setAutoCancel(true)
            builder.setSmallIcon(R.drawable.earth)

            var managerCompat = NotificationManagerCompat.from(context)
            managerCompat.notify(1, builder.build())

            context.contentResolver.update(
                ContentUris.withAppendedId(COUNTRY_PROVIDER_CONTENT_URI,item._id!!),
                ContentValues().apply {
                    put(Country::favorite.name, item.favorite)
                },
                null, null
            )
            notifyItemChanged(position)
        }

        holder.bind(item)
    }


    override fun getItemCount() = items.size
}