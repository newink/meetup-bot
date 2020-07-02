package dev.ivnv.meetup.modules

import dev.ivnv.meetup.Bot
import dev.ivnv.meetup.config.ConfigurationReader
import dev.ivnv.meetup.config.FreemarkerConfiguration
import dev.ivnv.meetup.database.DatabaseConfigurer
import dev.ivnv.meetup.dispatcher.Dispatcher
import dev.ivnv.meetup.service.ErrorHandler
import dev.ivnv.meetup.service.StateService
import dev.ivnv.meetup.service.TextService
import dev.ivnv.meetup.service.UserService
import org.koin.dsl.module

private val tgModule = module(createdAtStart = true) {
    single { Bot() }
}

private val serviceModule = module(createdAtStart = true) {
    single { UserService(get()) }
    single { ErrorHandler(get(), get()) }
    single { TextService(get()) }
    single { StateService(get(), get()).buildStateMachine() }
}

private val configModule = module(createdAtStart = true) {
    single { ConfigurationReader().read() }
    single { FreemarkerConfiguration().configure() }
    single { DatabaseConfigurer().configure() }
}

private val dispatcherModule = module(createdAtStart = true) {
    single { Dispatcher(get()).build() }
}

val declaredModules = listOf(tgModule, serviceModule, configModule, dispatcherModule)
