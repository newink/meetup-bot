package dev.ivnv.meetup.logging

import org.koin.core.logger.Level
import org.koin.core.logger.Logger
import org.koin.core.logger.MESSAGE

class Slf4jKoinLogger : Logger() {

    private val logger by LoggerDelegate()

    override fun log(level: Level, msg: MESSAGE) {
        when (level) {
            Level.DEBUG -> logger.debug(msg)
            Level.INFO -> logger.info(msg)
            Level.ERROR -> logger.error(msg)
            Level.NONE -> {
            }
        }
    }
}
