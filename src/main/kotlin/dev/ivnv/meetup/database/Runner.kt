package dev.ivnv.meetup.database

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class Runner {

    fun migrate() {
        Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")

        transaction {
            addLogger(Slf4jSqlDebugLogger)

            SchemaUtils.createMissingTablesAndColumns(Users)

            val userId = Users.insert {
                it[name] = "user"
                it[telegramId] = -1
            } get Users.id

            println("Users: ${Users.selectAll().joinToString {
                "${it[Users.id]} - ${it[Users.telegramId]}"
            }}")
        }
    }
}
