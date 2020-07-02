package dev.ivnv.meetup.database

import org.jetbrains.exposed.sql.Table

object Users : Table() {
    val id = long("telegram_id")
    val name = varchar("name", 255)
    val languageCode = enumerationByName("lang_code", 5, Lang::class)

    override val primaryKey = PrimaryKey(id, name = "pk_users_tg_id")
}

enum class Lang {
    RU, EN
}
