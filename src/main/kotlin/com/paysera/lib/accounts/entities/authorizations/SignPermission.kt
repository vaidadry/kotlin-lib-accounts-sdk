package com.paysera.lib.accounts.entities.authorizations

data class SignPermission(
    val level: String,
    val dayLimit: AuthorizationLimit?,
    val monthLimit: AuthorizationLimit?,
    val yearLimit: AuthorizationLimit?,
    val forAutomaticTransfers: Boolean
)