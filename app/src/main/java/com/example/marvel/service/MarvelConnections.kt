package com.example.marvel.service

import android.provider.Settings.Global.getString
import com.example.marvel.R

class MarvelConnections {

    val publicApiKey = "6d923a3ce3e227199e17c1ea42401f0a"

    var marvelUrl = "https://gateway.marvel.com:443/v1/public/"
    var paramCharacter = "characters?"
    var characterName = "nameStartsWith="
    var timestamp = "ts="
    var apiKey = "apikey="
    var hashCode = "hash="

    fun connCharactereByName(
        name: String,
        ts: String,
        hash: String
    ): String? {
        return marvelUrl +
                paramCharacter +
                characterName +
                name + "&" +
                timestamp +
                ts + "&" +
                apiKey +
                publicApiKey + "&" +
                hashCode +
                hash
    }

    fun connComics(
        comicsPath: String,
        ts: String,
        hash: String
    ): String? {
        return comicsPath + "?" +
                timestamp +
                ts + "&" +
                apiKey +
                publicApiKey + "&" +
                hashCode +
                hash
    }

    fun connSeries(
        seriesPath: String,
        ts: String,
        hash: String
    ): String? {
        return seriesPath + "?" +
                timestamp +
                ts + "&" +
                apiKey +
                publicApiKey + "&" +
                hashCode +
                hash
    }
}