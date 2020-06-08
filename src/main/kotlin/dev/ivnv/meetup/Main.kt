package dev.ivnv.meetup

import dev.ivnv.meetup.modules.configModule
import dev.ivnv.meetup.modules.dispatcherModule
import dev.ivnv.meetup.modules.tgModule
import org.koin.core.context.startKoin

fun main() {
    startKoin {
        printLogger()
        modules(tgModule, configModule, dispatcherModule)
    }

    Bot().start()
}


