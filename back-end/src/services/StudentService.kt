package com.gabriel.kspanel.project.rest.services

import com.gabriel.kspanel.project.rest.entities.Student
import com.gabriel.kspanel.project.rest.entities.StudentCreateDTO
import com.gabriel.kspanel.project.rest.entities.StudentUpdateDTO
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

class StudentService {

    suspend fun storeWithId(create: StudentCreateDTO): Student = newSuspendedTransaction {
         Student.new {
             name = create.name
        }
    }

    suspend fun updateById(id: Int, update: StudentUpdateDTO) = newSuspendedTransaction {
        findById(id)?.apply {
            name = update.name ?: name
        }
    }

    suspend fun findById(id: Int): Student? = newSuspendedTransaction {
        Student.findById(id)
    }

    suspend fun deleteById(id: Int) = newSuspendedTransaction {
        findById(id)?.delete()
    }
}