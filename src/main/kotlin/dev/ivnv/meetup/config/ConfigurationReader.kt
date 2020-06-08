package dev.ivnv.meetup.config

import com.google.gson.Gson

class ConfigurationReader {

    fun read(): Configuration? {
        val gson = Gson()
        val jsonText = this.javaClass.classLoader.getResource("config.json").readText() // todo external file
        return gson.fromJson(jsonText, Configuration::class.java)
    }
}
