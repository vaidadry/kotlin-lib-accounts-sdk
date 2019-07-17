package com.paysera.lib.accounts.entities.authorizations

data class CreateAuthorizationRequest(
    val accountNumber: String,
    val userIds: List<String>,
    val readPermission: Boolean,
    val writePermission: Boolean,
    val signPermission: SignPermission?
)