package dev.ivnv.meetup.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Slf4jSqlDebugLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

class DatabaseConfigurer {

    fun init() {
        Database.connect(hikari())
        transaction {
            addLogger(Slf4jSqlDebugLogger)
            SchemaUtils.createMissingTablesAndColumns(Users)
        }
    }

    private fun hikari(): HikariDataSource {
        val config = HikariConfig()
        config.driverClassName = "org.h2.Driver"
        config.jdbcUrl = "jdbc:h2:mem:test"
        config.maximumPoolSize = 3
        config.validate()

        return HikariDataSource(config)
    }
}
