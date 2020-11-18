@file:OptIn(KtorExperimentalAPI::class, KtorExperimentalLocationsAPI::class)

package com.gabriel.kspanel.project.rest.authentication.auth.route

import com.gabriel.kspanel.project.rest.authentication.auth.AuthenticationService
import io.ktor.application.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.*

@OptIn(KtorExperimentalAPI::class, KtorExperimentalLocationsAPI::class)
fun Route.authenticationRoutes(authenticationService: AuthenticationService) {
    post<Authentications> {
        call.respond(authenticationService.handle(call.receive()))
    }
}

@Location("api/authentication")
class Authentications