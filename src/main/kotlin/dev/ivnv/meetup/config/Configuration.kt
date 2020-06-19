package dev.ivnv.meetup.config

import java.net.Authenticator
import java.net.PasswordAuthentication
import java.net.Proxy.Type as ProxyType

data class Configuration(
    val botToken: String,
    val proxy: Proxy
)

data class Proxy(
    val username: String,
    val password: String,
    val host: String,
    val port: Int,
    val type: ProxyType
)

class ProxyAuth(private val configuration: Configuration) : Authenticator() {
    override fun getPasswordAuthentication(): PasswordAuthentication {
        return PasswordAuthentication(configuration.proxy.username, configuration.proxy.password.toCharArray())
    }
}
