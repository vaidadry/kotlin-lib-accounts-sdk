package com.paysera.lib.accounts.entities.cards

data class CardAccount(
    val number: String,
    val clientId: Int,
    val ownerId: Int,
    val createdAt: Int,
    val active: Boolean,
    val closed: Boolean,
    val type: String,
    val ibanList: List<String>,
    val client: CardClient
)