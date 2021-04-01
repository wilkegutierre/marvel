package com.example.marvel.service

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.util.Log
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.collections.HashMap

@Suppress("DEPRECATION")
class MarvelCharacters(private val context: Context) :
    AsyncTask<String, String, Map<String, Any>>() {

    lateinit var progress: ProgressDialog

    override fun onPreExecute() {
        super.onPreExecute()

        progress = ProgressDialog(context)
        progress.setMessage("Load Heros...")
        progress.setCancelable(false)
        progress.show()
    }

    override fun doInBackground(vararg params: String?): Map<String, Any> {
        val url: String? = params[0]
        val connection = URL(url).openConnection() as HttpURLConnection
        var map: Map<String, Any> = HashMap()

        try {
            connection.connectTimeout = 3000
            connection.readTimeout = 3000
            connection.requestMethod = "GET"
            connection.doInput = true
            connection.connect()

            var retorno = streamToString(connection.inputStream)
            map = Gson().fromJson(retorno, map.javaClass)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        } catch (ex: Exception) {
            Log.d("Error", ex.toString())
        }

        return map
    }

    override fun onPostExecute(result: Map<String, Any>?) {
        super.onPostExecute(result)
        progress.dismiss()
    }

    fun streamToString(inputStream: InputStream): String {

        val bufferReader = BufferedReader(InputStreamReader(inputStream))
        var line: String
        var result = ""

        try {
            do {
                line = bufferReader.readLine()
                if (line != null) {
                    result += line
                }
            } while (line != null)
            inputStream.close()
        } catch (ex: Exception) {

        }

        return result
    }
}