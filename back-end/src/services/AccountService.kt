package com.gabriel.kspanel.project.rest.services

import com.gabriel.kspanel.project.rest.entities.Account
import com.gabriel.kspanel.project.rest.entities.AccountCreateDTO
import com.gabriel.kspanel.project.rest.entities.AccountUpdateDTO
import com.gabriel.kspanel.project.rest.utils.user.createNewPassword
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

class AccountService {

    suspend fun storeWithId(create: AccountCreateDTO): Account = newSuspendedTransaction {
         Account.new {
             name = create.name
             password = createNewPassword()
         }
    }

    suspend fun updateById(id: Int, update: AccountUpdateDTO) = newSuspendedTransaction {
        findById(id)?.apply {
            name = update.name ?: name
            password = update.password ?: password
        }
    }

    suspend fun findById(id: Int): Account? = newSuspendedTransaction {
        Account.findById(id)
    }

    suspend fun deleteById(id: Int) = newSuspendedTransaction {
        findById(id)?.delete()
    }
}