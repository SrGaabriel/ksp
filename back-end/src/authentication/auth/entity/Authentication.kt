package com.gabriel.kspanel.project.rest.authentication.auth.entity

import kotlinx.serialization.Serializable

@Serializable
data class Authentication(
    val id: Int,
    val password: String
)