package com.paysera.lib.accounts.entities.authorizations

data class SignPermission(
    val level: String,
    val dayLimit: Limit?,
    val monthLimit: Limit?,
    val yearLimit: Limit?,
    val forAutomaticTransfers: Boolean
)