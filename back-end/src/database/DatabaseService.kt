package com.gabriel.kspanel.project.rest.database

import com.gabriel.kspanel.project.rest.database.tables.BracketTable
import com.gabriel.kspanel.project.rest.database.tables.StudentTable
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.config.*
import io.ktor.util.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

@OptIn(KtorExperimentalAPI::class)
class DatabaseService(private val config: ApplicationConfig) {

    fun connect() {
        val dataSource = HikariDataSource(HikariConfig().apply {
            jdbcUrl = "jdbc:mysql://${value("host")}:${value("port")}/${value("database")}?useTimezone=true&serverTimezone=UTC"
            username = value("user")
            password = value("password")
        })

        Database.connect(dataSource)
    }

    fun createTables() = transaction {
        SchemaUtils.createMissingTablesAndColumns(StudentTable, BracketTable)
    }

    private fun value(name: String) = config.property(name).getString()

}