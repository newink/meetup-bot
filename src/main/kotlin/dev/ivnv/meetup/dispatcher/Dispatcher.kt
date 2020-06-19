package dev.ivnv.meetup.dispatcher

import com.github.kotlintelegrambot.dispatcher.Dispatcher
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.dispatcher.sticker
import com.github.kotlintelegrambot.dispatcher.text
import dev.ivnv.meetup.logging.LoggerDelegate
import org.jetbrains.exposed.sql.transactions.transaction


class Dispatcher {

    private val log by LoggerDelegate()

    fun build(): Dispatcher.() -> Unit = {
        text { bot, update ->
            log.info("{}, {}", update)
        }
        sticker { bot, update, sticker ->
            log.info("{}, {}", update)
        }
        command("start") { bot, update, list ->

            transaction {

            }

            log.info("{}, {}", update, list)
        }
    }
}


