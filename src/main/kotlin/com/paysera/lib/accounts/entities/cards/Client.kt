package com.paysera.lib.accounts.entities.cards

class Client(
    val id: Int,
    val userId: Int,
    val createdAt: Int,
    val enabled: Boolean,
    val displayName: String,
    val type: String
)