package com.gabriel.kspanel.project.rest.services

import com.gabriel.kspanel.project.rest.entities.Bracket
import com.gabriel.kspanel.project.rest.entities.BracketCreateDTO
import com.gabriel.kspanel.project.rest.entities.BracketUpdateDTO
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

class BracketService {

    suspend fun storeWithId(create: BracketCreateDTO): Bracket = newSuspendedTransaction {
        Bracket.new {
            index = create.index
            grade = create.grade
        }
    }

    suspend fun updateById(id: Int, update: BracketUpdateDTO) = newSuspendedTransaction {
        findById(id)?.apply {
            index = update.index ?: index
            grade = update.grade ?: grade
        }
    }

    suspend fun findById(id: Int): Bracket? = newSuspendedTransaction {
        Bracket.findById(id)
    }

    suspend fun deleteById(id: Int) = newSuspendedTransaction {
        findById(id)?.delete()
    }
}