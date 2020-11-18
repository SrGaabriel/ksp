package com.gabriel.kspanel.project.rest.utils

import com.gabriel.kspanel.project.rest.utils.user.Permission
import com.gabriel.kspanel.project.rest.utils.user.PermissionSet
import kotlinx.serialization.Serializable

@Serializable
enum class AccountType(val permissions: PermissionSet) {

    DIRECTOR(PermissionSet {
        listOf(
            Permission.Administrator,
        )
    }),
    COORDINATOR(PermissionSet {
        listOf(
            Permission.CreateAssignments,
            Permission.ManageAccounts
        )
    }),
    TEACHER(PermissionSet {
        listOf(
            Permission.CreateAssignments,
            Permission.ManageAccounts
        )
    }),
    ASSISTANT(PermissionSet {
        emptyList()
    }),
    STUDENT(PermissionSet {
        emptyList()
    });
}