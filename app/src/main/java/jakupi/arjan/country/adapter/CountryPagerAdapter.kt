package jakupi.arjan.country.adapter

import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import jakupi.arjan.country.COUNTRY_PROVIDER_CONTENT_URI
import jakupi.arjan.country.R
import jakupi.arjan.country.model.Country
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import java.io.File
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
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.country_pager, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.ivRead.setOnClickListener {
            // update
            item.favorite = !item.favorite

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
/*class CountryPagerAdapter(private val context: Context, private val countries: MutableList<Country>)
    : RecyclerView.Adapter<CountryPagerAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivFlag = itemView.findViewById<ImageView>(R.id.ivCountryFlag)
        val ivFavorite = itemView.findViewById<ImageView>(R.id.ivFavorite)

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
                .into(ivFlag)
            tvName.text = item.name
            tvCapital.text = item.capital
            tvContinent.text = item.continents
            tvTimezone.text = item.timezone
            tvPopulation.text = item.population.toString()
            ivFavorite.setImageResource(if(item.favorite) R.drawable.heart_fillpng else R.drawable.heart_nofill)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_country_pager, parent, false)
        )    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = countries[position]

        /*holder.ivFavorite.setOnClickListener {
            // update
            country.favorite = !country.favorite

            context.contentResolver.update(
                ContentUris.withAppendedId(COUNTRY_PROVIDER_CONTENT_URI,country._id!!),
                ContentValues().apply {
                    put(Country::favorite.name, country.favorite)
                },
                null, null
            )
            notifyItemChanged(position)
        }*/

        holder.bind(country)
    }

    override fun getItemCount()= countries.size
}

*/