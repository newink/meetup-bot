package dev.ivnv.meetup.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import javax.sql.DataSource

class DatabaseConfigurer {

    fun configure(): DataSource {
        val datasource = hikari()
        Database.connect(datasource)
        transaction {
            SchemaUtils.create(Users)
        }
        return datasource
    }

    private fun hikari(): HikariDataSource {
        val config = HikariConfig()
        config.driverClassName = "org.h2.Driver"
        config.jdbcUrl = "jdbc:h2:./test"
        config.username = "sa"
        config.password = "sa"
        config.maximumPoolSize = 3
        config.validate()

        return HikariDataSource(config)
    }
}
