package com.paysera.lib.accounts.entities

data class AvailableCurrency(
    val code: String,
    val features: List<String>,
    val type: String
)