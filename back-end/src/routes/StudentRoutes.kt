@file:OptIn(KtorExperimentalAPI::class, KtorExperimentalLocationsAPI::class)

package com.gabriel.kspanel.project.rest.routes

import com.gabriel.kspanel.project.rest.authentication.authenticated
import com.gabriel.kspanel.project.rest.services.StudentService
import io.ktor.application.*
import io.ktor.config.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.*

@OptIn(KtorExperimentalAPI::class, KtorExperimentalLocationsAPI::class)
fun Route.studentRoutes(config: ApplicationConfig, studentService: StudentService) = authenticated(config) {
    put<Students.Id> { (id) ->
        when (val student = studentService.updateById(id, call.receive())) {
            null -> call.respond(HttpStatusCode.NotFound)
            else -> call.respond(student.toResponseDto())
        }
    }
    post<Students> {
        call.respond(HttpStatusCode.Created, studentService.storeWithId(call.receive()).toResponseDto())
    }
    get<Students.Id> { (id) ->
        when (val student = studentService.findById(id)) {
            null -> call.respond(HttpStatusCode.NotFound)
            else -> call.respond(student.toResponseDto())
        }
    }
    delete<Students.Id> { (id) ->
        call.respond(HttpStatusCode.NoContent, studentService.deleteById(id).let { Unit })
    }
}

@Location("/api/students")
class Students {

    @Location("/{id}")
    data class Id(val id: Int, val students: Students)

}