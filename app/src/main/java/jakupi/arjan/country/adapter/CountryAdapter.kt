package jakupi.arjan.country.adapter

import android.content.ContentUris
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import jakupi.arjan.country.COUNTRY_PROVIDER_CONTENT_URI
import jakupi.arjan.country.CountryPagerActivity
import jakupi.arjan.country.POSITION
import jakupi.arjan.country.R
import jakupi.arjan.country.framework.startActivity
import jakupi.arjan.country.model.Country
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import java.io.File

class CountryAdapter(private val context: Context, private val countries: MutableList<Country>)
    :   RecyclerView.Adapter<CountryAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivFlag = itemView.findViewById<ImageView>(R.id.ivFlag)
        private val tvName = itemView.findViewById<TextView>(R.id.tvName)

        fun bind(item: Country) {
            Picasso.get()
                .load(File(item.flagPath))
                .error(R.drawable.robot_ballon)
                .transform(RoundedCornersTransformation(50, 5))
                .into(ivFlag)
            tvName.text = item.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.country, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = countries[position]

        holder.itemView.setOnLongClickListener{
            deleteCountry(position)
            true
        }

        holder.itemView.setOnClickListener{
            context.startActivity<CountryPagerActivity>(POSITION, position)
        }

        holder.bind(country)
    }

    private fun deleteCountry(position: Int) {
        val country = countries[position]
        context.contentResolver.delete(
            ContentUris.withAppendedId(COUNTRY_PROVIDER_CONTENT_URI, country._id!!),
            null,
            null
        )
        File(country.flagPath).delete()
        countries.removeAt(position)
        notifyDataSetChanged()
    }

    override fun getItemCount() = countries.size
}