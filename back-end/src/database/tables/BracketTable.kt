package com.gabriel.kspanel.project.rest.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object BracketTable: IntIdTable(name = "bracketTable") {

    val index = integer("index")
    val grade = integer("grade")

}