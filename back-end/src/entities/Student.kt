package com.gabriel.kspanel.project.rest.entities

import com.gabriel.kspanel.project.rest.database.tables.StudentTable
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Student(id: EntityID<Int>): IntEntity(id) {

    companion object: IntEntityClass<Student>(StudentTable)

    var name by StudentTable.name

    fun toResponseDto() = StudentResponseDTO(id.value, name)

}

@Serializable
data class StudentCreateDTO(
    val name: String,
)

@Serializable
data class StudentUpdateDTO(
    val name: String
)

@Serializable
data class StudentResponseDTO(
    val id: Int,
    val name: String
)