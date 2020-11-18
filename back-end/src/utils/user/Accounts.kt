package com.gabriel.kspanel.project.rest.utils.user

import com.gabriel.kspanel.project.rest.utils.AccountType
import java.util.concurrent.ThreadLocalRandom

fun AccountType.isStaff(): Boolean =
    this != AccountType.STUDENT

fun createNewPassword() =
    ThreadLocalRandom.current().nextInt(9999999, 99999999)