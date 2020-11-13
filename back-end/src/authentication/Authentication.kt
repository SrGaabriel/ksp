@file:OptIn(KtorExperimentalAPI::class)

package com.gabriel.kspanel.project.rest.authentication

import io.ktor.application.*
import io.ktor.config.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.*
import io.ktor.util.pipeline.*

@OptIn(KtorExperimentalAPI::class)
fun Route.authenticated(config: ApplicationConfig, route: Route.() -> Unit): Route {
    intercept(ApplicationCallPipeline.Features) {
        validate(config, call.request.authorization().orEmpty()) {
            finish()
        }
    }
    return apply(route)
}

@OptIn(KtorExperimentalAPI::class)
private suspend fun PipelineContext<Unit, ApplicationCall>.validate(config: ApplicationConfig, value: String, callback: (String) -> Unit) = if (config.property("authentication-keys").getList().contains(value).not())
    call.respond(HttpStatusCode.Unauthorized).also { callback(value) } else Unit