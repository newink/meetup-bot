package dev.ivnv.meetup

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.Dispatcher
import dev.ivnv.meetup.config.Configuration
import dev.ivnv.meetup.config.ProxyAuth
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.net.Authenticator
import java.net.InetSocketAddress
import java.net.Proxy

class Bot : KoinComponent {

    private val config: Configuration by inject()
    private val dispatcher: Dispatcher.() -> Unit by inject()

    fun start() {
        configureBot().startPolling()
    }

    private fun configureBot(): Bot = bot {
        token = config.botToken
        timeout = 5
        logLevel = HttpLoggingInterceptor.Level.BODY
        proxy = configureProxy()
        dispatch { dispatcher(this) }
    }

    private fun configureProxy(): Proxy {
        Authenticator.setDefault(ProxyAuth(config))
        return Proxy(config.proxy.type, InetSocketAddress(config.proxy.host, config.proxy.port))
    }
}
