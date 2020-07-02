package dev.ivnv.meetup.config

import com.google.gson.Gson
import java.io.File


class ConfigurationReader {

    private val gson: Gson = Gson()

    fun read(): Configuration {
        return gson.fromJson(readFromFile(), Configuration::class.java)
    }

    private fun readFromFile(): String? {
        val filePath = System.getProperty("configFile")
        return if (filePath.isNullOrBlank())
            this.javaClass.classLoader.getResource("config.json")?.readText()
        else File(filePath).readText()
    }
}
