package com.paysera.lib.accounts.entities.authorizations

data class Authorization(
    val id: String,
    val accountNumber: String,
    val users: List<User>,
    val readPermission: Boolean,
    val writePermission: Boolean,
    val signPermission: SignPermission?,
    val validFrom: Int,
    val validTo: Int?,
    val replacedAuthorizationId: String?
)