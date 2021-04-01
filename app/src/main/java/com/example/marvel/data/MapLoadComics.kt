package com.example.marvel.data

import android.content.Context
import android.util.Log
import com.example.marvel.service.MarvelCharacters
import java.lang.Exception

class MapLoadData {

    fun loadComics(url: String?, context: Context): ArrayList<Map<String, Any>> {
        var map: Map<String, Any>
        var mapData: Map<String, Any>
        var resultList = ArrayList<Map<String, Any>>()
        var mapHero: Map<String, Any>

        val marvelConnection = MarvelCharacters(context).execute(url)
        map = marvelConnection.get()

        try {
            for (key in map.keys) {
                if (key == "data") {
                    mapData = map["data"] as Map<String, Any>
                    for (keyData in mapData.keys) {
                        if (keyData == "results") {
                            resultList = mapData["results"] as ArrayList<Map<String, Any>>
                        }
                    }
                }
            }
        } catch (ex: Exception) {
            Log.e("Map", ex.toString())
        }

        return resultList
    }
}