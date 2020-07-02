package dev.ivnv.meetup.service

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.entities.Update
import dev.ivnv.meetup.database.Users
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction


class UserService(private val textService: TextService) {

    fun handleRegister(bot: Bot, update: Update) {
        val (telegramId, _, _, _, username, _) = update.message!!.from!!
        val lang = update.getLanguageCode()
        transaction {
            Users.insert {
                it[id] = telegramId
                it[languageCode] = lang
                it[name] = username!!
            }
        }
        bot.respondWithMarkdown(update, textService.render(lang, TemplateKey.REGISTRATION_SUCCESSFUL))
    }
}
