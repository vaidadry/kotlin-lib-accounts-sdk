package com.paysera.lib.accounts.entities

data class Account(
    val number: String,
    val clientId: Int,
    val ownerId: Int,
    val active: Boolean,
    val closed: Boolean,
    val type: String,
    val ibanList: List<String>
)