package jakupi.arjan.country

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import jakupi.arjan.country.dao.CountryRepository
import jakupi.arjan.country.factory.getCountryRepository
import jakupi.arjan.country.model.Country

private const val AUTHORITY = "jakupi.arjan.country.api.provider"
private const val PATH = "items"
val COUNTRY_PROVIDER_CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/$PATH")

private const val ITEMS = 10
private const val ITEM_ID = 20
private val URI_MATCHER = with(UriMatcher(UriMatcher.NO_MATCH)) {
    addURI(AUTHORITY, PATH, ITEMS)
    addURI(AUTHORITY, "$PATH/#", ITEM_ID)
    this
}

class CountryProvider : ContentProvider() {
    private lateinit var countryRepository: CountryRepository

    override fun onCreate(): Boolean {
        countryRepository = getCountryRepository(context)
        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? = countryRepository.query(projection, selection, selectionArgs, sortOrder)

    override fun getType(p0: Uri): String? {
        TODO(
            "Implement this to handle requests for the MIME type of the data" +
                    "at the given URI"
        )
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val id = countryRepository.insert(values)
        return ContentUris.withAppendedId(COUNTRY_PROVIDER_CONTENT_URI, id)
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        when(URI_MATCHER.match(uri)) {
            ITEMS -> return countryRepository.delete(selection, selectionArgs)
            ITEM_ID ->
                uri.lastPathSegment?.let {
                    return countryRepository.delete("${Country::_id.name}=?", arrayOf(it))
                }
        }
        throw java.lang.IllegalArgumentException("No such uri")
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        when(URI_MATCHER.match(uri)) {
            ITEMS -> return countryRepository.update(values, selection, selectionArgs)
            ITEM_ID ->
                uri.lastPathSegment?.let {
                    return countryRepository.update(values, "${Country::_id.name}=?", arrayOf(it))
                }
        }
        throw java.lang.IllegalArgumentException("No such uri")
    }
}