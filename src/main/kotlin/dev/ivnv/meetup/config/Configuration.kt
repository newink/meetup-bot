package dev.ivnv.meetup.config

data class Configuration(
    val botToken: String,
    val proxy: Proxy
)

data class Proxy(
    val host: String,
    val port: Int
)

