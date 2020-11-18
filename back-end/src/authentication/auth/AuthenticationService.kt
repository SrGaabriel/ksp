package com.gabriel.kspanel.project.rest.authentication.auth

import com.gabriel.kspanel.project.rest.authentication.auth.entity.Authentication
import com.gabriel.kspanel.project.rest.services.AccountService
import io.ktor.http.*

class AuthenticationService(private val accountService: AccountService) {

    suspend fun handle(authentication: Authentication): HttpStatusCode {
        val account = accountService.findById(authentication.id)
        val password = account?.password ?: HttpStatusCode.NotFound

        return if (password.toString() != authentication.password)
            HttpStatusCode.Unauthorized
        else HttpStatusCode.Accepted
    }

}