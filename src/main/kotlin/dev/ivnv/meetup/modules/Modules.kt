package dev.ivnv.meetup.modules

import dev.ivnv.meetup.Bot
import dev.ivnv.meetup.config.ConfigurationReader
import dev.ivnv.meetup.dispatcher.Router
import org.koin.dsl.module

val tgModule = module {
    single { Bot() }
}

val configModule = module {
    single { ConfigurationReader().read() }
}

val dispatcherModule = module {
    single { Router().build() }
}
