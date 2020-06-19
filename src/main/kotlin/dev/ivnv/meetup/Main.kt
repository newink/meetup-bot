package dev.ivnv.meetup

import dev.ivnv.meetup.logging.Slf4jKoinLogger
import dev.ivnv.meetup.modules.configModule
import dev.ivnv.meetup.modules.dispatcherModule
import dev.ivnv.meetup.modules.tgModule
import org.koin.core.context.startKoin

fun main() {
    startKoin {
        logger(Slf4jKoinLogger())
        modules(tgModule, configModule, dispatcherModule)
    }
    Bot().start()
}


