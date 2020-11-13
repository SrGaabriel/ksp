package com.gabriel.kspanel.project.rest.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object StudentTable: IntIdTable(name = "studentsTable") {

    val name = varchar("name", 80)

}