package dev.ivnv.meetup.modules

import dev.ivnv.meetup.Bot
import dev.ivnv.meetup.config.ConfigurationReader
import dev.ivnv.meetup.database.DatabaseConfigurer
import dev.ivnv.meetup.dispatcher.Dispatcher
import org.koin.dsl.module

val tgModule = module(createdAtStart = true) {
    single { Bot() }
}

val configModule = module(createdAtStart = true) {
    single { ConfigurationReader().read() }
    single { DatabaseConfigurer() }
}

val dispatcherModule = module(createdAtStart = true) {
    single { Dispatcher().build() }
}
