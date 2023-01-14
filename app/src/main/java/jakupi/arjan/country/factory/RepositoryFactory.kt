package jakupi.arjan.country.factory

import android.content.Context
import jakupi.arjan.country.dao.CountrySqlHelper

fun getCountryRepository(context: Context?) = CountrySqlHelper(context)