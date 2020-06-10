package com.paysera.lib.accounts.entities.authorizations

data class UserValidationResult(
    val userId: String,
    val valid: Boolean,
    val reason: String
)