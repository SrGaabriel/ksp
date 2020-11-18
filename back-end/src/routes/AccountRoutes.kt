@file:OptIn(KtorExperimentalAPI::class, KtorExperimentalLocationsAPI::class)

package com.gabriel.kspanel.project.rest.routes

import com.gabriel.kspanel.project.rest.authentication.authenticated
import com.gabriel.kspanel.project.rest.services.AccountService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.*

@OptIn(KtorExperimentalAPI::class, KtorExperimentalLocationsAPI::class)
fun Route.accountRoutes(accountService: AccountService) = authenticated {
    put<Accounts.Id> { (id) ->
        when (val account = accountService.updateById(id, call.receive())) {
            null -> call.respond(HttpStatusCode.NotFound)
            else -> call.respond(account.toResponseDto())
        }
    }
    post<Accounts> {
        call.respond(HttpStatusCode.Created, accountService.storeWithId(call.receive()).toResponseDto())
    }
    get<Accounts.Id> { (id) ->
        when (val account = accountService.findById(id)) {
            null -> call.respond(HttpStatusCode.NotFound)
            else -> call.respond(account.toResponseDto())
        }
    }
    delete<Accounts.Id> { (id) ->
        call.respond(HttpStatusCode.NoContent, accountService.deleteById(id).let { Unit })
    }
}

@Location("/api/accounts")
class Accounts {

    @Location("/{id}")
    data class Id(val id: Int, val accounts: Accounts)

}