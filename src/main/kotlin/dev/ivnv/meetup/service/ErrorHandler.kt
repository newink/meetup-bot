package dev.ivnv.meetup.service

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.entities.Update
import dev.ivnv.meetup.config.Configuration
import dev.ivnv.meetup.logging.LoggerDelegate

class ErrorHandler(
    private val textService: TextService,
    private val configuration: Configuration
) {

    private val log by LoggerDelegate()

    fun handleError(bot: Bot, update: Update, exception: Exception) {
        log.error("Unhandled exception: {}", exception)
        if (configuration.debug) {
            bot.respondWithPlainText(update, "Exception: $exception")
        }
        bot.respondWithPlainText(update, textService.render(update.getLanguageCode(), TemplateKey.ERROR))
    }
}
