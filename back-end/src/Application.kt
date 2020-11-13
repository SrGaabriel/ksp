@file:OptIn(KtorExperimentalAPI::class, KtorExperimentalLocationsAPI::class)

package com.gabriel.kspanel.project.rest

import com.gabriel.kspanel.project.rest.database.DatabaseService
import com.gabriel.kspanel.project.rest.routes.studentRoutes
import com.gabriel.kspanel.project.rest.services.StudentService
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.util.*
import org.slf4j.event.Level

fun main(args: Array<String>): Unit = io.ktor.server.jetty.EngineMain.main(args)

@Suppress("unused")
@OptIn(KtorExperimentalAPI::class, KtorExperimentalLocationsAPI::class)
fun Application.module() {
    install(CORS) {
        anyHost()
        allowCredentials = true

        header("Content-Type")
        header(HttpHeaders.Authorization)

        method(HttpMethod.Options)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
    }

    install(Locations)
    install(ContentNegotiation) {
        json()
    }

    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }

    DatabaseService(environment.config.config("database")).let {
        it.connect()
        it.createTables()
    }

    authentication {
    }

    val studentService = StudentService()

    routing {
        studentRoutes(environment.config, studentService)
    }
}

