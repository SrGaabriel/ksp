package com.gabriel.kspanel.project.rest.entities

import com.gabriel.kspanel.project.rest.database.tables.AccountTable
import com.gabriel.kspanel.project.rest.utils.AccountType
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Account(id: EntityID<Int>): IntEntity(id) {

    companion object: IntEntityClass<Account>(AccountTable)

    var name by AccountTable.name
    var type by AccountTable.type
    var password by AccountTable.password

    fun toResponseDto() = AccountResponseDTO(id.value, name, type)

}

@Serializable
data class AccountCreateDTO(
    val name: String,
    val type: AccountType = AccountType.STUDENT
)

@Serializable
data class AccountUpdateDTO(
    val name: String?,
    val type: AccountType?,
    val password: Int?
)

@Serializable
data class AccountResponseDTO(
    val id: Int,
    val name: String,
    val type: AccountType
)