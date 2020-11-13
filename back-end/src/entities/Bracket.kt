package com.gabriel.kspanel.project.rest.entities

import com.gabriel.kspanel.project.rest.database.tables.BracketTable
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Bracket(id: EntityID<Int>): IntEntity(id) {

    companion object: IntEntityClass<Bracket>(BracketTable)

    var index by BracketTable.index
    var grade by BracketTable.grade

    fun toResponseDto() = BracketResponseDTO(id.value, index, grade)

}

@Serializable
data class BracketCreateDTO(
    val index: Int,
    val grade: Int
)

@Serializable
data class BracketUpdateDTO(
    val index: Int?,
    val grade: Int?
)

@Serializable
data class BracketResponseDTO(
    val id: Int,
    val index: Int,
    val grade: Int
)