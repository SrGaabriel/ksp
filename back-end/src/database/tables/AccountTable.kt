package com.gabriel.kspanel.project.rest.database.tables

import com.gabriel.kspanel.project.rest.utils.AccountType
import org.jetbrains.exposed.dao.id.IntIdTable

object AccountTable: IntIdTable(name = "accountsTable") {

    val name = varchar("name", 80)
    val type = enumeration("type", AccountType::class)
    val password = integer("password")

}