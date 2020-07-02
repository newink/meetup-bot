package dev.ivnv.meetup

import dev.ivnv.meetup.logging.Slf4jKoinLogger
import dev.ivnv.meetup.modules.declaredModules
import org.koin.core.context.startKoin

fun main() {
    startKoin {
        logger(Slf4jKoinLogger())
        modules(declaredModules)
    }
    Bot().start()
}


