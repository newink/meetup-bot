package dev.ivnv.meetup.service

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.entities.ParseMode
import com.github.kotlintelegrambot.entities.Update
import dev.ivnv.meetup.database.Lang


fun Bot.respondWithPlainText(update: Update, text: String) {
    this.sendMessage(update.message!!.chat.id, text)
}

fun Bot.respondWithHtml(update: Update, text: String) {
    this.sendMessage(update.message!!.chat.id, text, parseMode = ParseMode.HTML)
}

fun Bot.respondWithMarkdown(update: Update, text: String) {
    this.sendMessage(update.message!!.chat.id, text, parseMode = ParseMode.MARKDOWN)
}

fun Update.getLanguageCode(): Lang {
    val languageCode = this.message?.from?.languageCode
    return if (languageCode != null) Lang.valueOf(languageCode.toUpperCase()) else Lang.RU
}
