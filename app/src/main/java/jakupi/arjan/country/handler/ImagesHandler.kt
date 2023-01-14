package jakupi.arjan.country.handler

import android.content.Context
import android.util.Log
import jakupi.arjan.country.factory.createGetHttpUrlConnection
import okhttp3.HttpUrl
import java.io.File
import java.net.HttpURLConnection
import java.nio.file.Files
import java.nio.file.Paths

fun downloadImageAndStore(context: Context, url: String): String? {
    //https://flagcdn.com/hr.svg
    val filename = url.substring(url.lastIndexOf("/") + 1)
    val file = createFile(context, filename)
    try{
        val con: HttpURLConnection = createGetHttpUrlConnection(url)
        Files.copy(con.inputStream, Paths.get(file.toURI()))
        return file.absolutePath
    }catch (e: Exception){
        Log.e("ImagesHandler", e.toString(), e)
    }
    return null
}

private fun createFile(context: Context, filename: String): File{
    val dir = context.applicationContext.getExternalFilesDir(null)
    val file = File(dir, filename)
    if( file.exists()) file.delete()
    return file
}