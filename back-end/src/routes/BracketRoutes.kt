@file:OptIn(KtorExperimentalAPI::class, KtorExperimentalLocationsAPI::class)

package com.gabriel.kspanel.project.rest.routes

import com.gabriel.kspanel.project.rest.authentication.authenticated
import com.gabriel.kspanel.project.rest.services.BracketService
import io.ktor.application.*
import io.ktor.config.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.*

@OptIn(KtorExperimentalAPI::class, KtorExperimentalLocationsAPI::class)
fun Route.bracketRoutes(config: ApplicationConfig, bracketService: BracketService) = authenticated(config) {
    put<Brackets.Id> { (id) ->
        when (val bracket = bracketService.updateById(id, call.receive())) {
            null -> call.respond(HttpStatusCode.NotFound)
            else -> call.respond(bracket.toResponseDto())
        }
    }
    post<Brackets> {
        call.respond(HttpStatusCode.Created, bracketService.storeWithId(call.receive()).toResponseDto())
    }
    get<Brackets.Id> { (id) ->
        when (val bracket = bracketService.findById(id)) {
            null -> call.respond(HttpStatusCode.NotFound)
            else -> call.respond(bracket.toResponseDto())
        }
    }
    delete<Brackets.Id> { (id) ->
        call.respond(HttpStatusCode.NoContent, bracketService.deleteById(id).let { Unit })
    }
}

@Location("/api/brackets")
class Brackets {

    @Location("/{id}")
    data class Id(val id: Int, val brackets: Brackets)

}