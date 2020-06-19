package dev.ivnv.meetup.database

import org.jetbrains.exposed.dao.id.LongIdTable

object Users : LongIdTable() {
    val telegramId = long("telegram_id").uniqueIndex("tg_id_idx")
    val name = varchar("name", 255)
}
