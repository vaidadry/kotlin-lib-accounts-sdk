package com.paysera.lib.accounts.entities.authorizations

import java.util.*

data class Authorization(
    val id: String,
    val accountNumber: String,
    val users: List<AuthorizationUser>,
    val readPermission: Boolean,
    val writePermission: Boolean,
    val signPermission: SignPermission?,
    val validFrom: Date,
    val validTo: Date?,
    val replacedAuthorizationId: String?
)