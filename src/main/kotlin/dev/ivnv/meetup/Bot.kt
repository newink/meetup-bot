package dev.ivnv.meetup

import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.Dispatcher
import dev.ivnv.meetup.config.Configuration
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.net.InetSocketAddress
import java.net.Proxy

class Bot : KoinComponent {

    private val config: Configuration by inject()
    private val dispatcher: Dispatcher.() -> Unit by inject()

    fun start() {
        val bot = bot {
            token = config.botToken
            logLevel = HttpLoggingInterceptor.Level.BODY
            proxy = Proxy(Proxy.Type.HTTP, InetSocketAddress(config.proxy.host, config.proxy.port))
            dispatch { dispatcher }
        }
        bot.startPolling()
    }
}
