package com.paysera.lib.accounts.entities.cards

class CardsFilter(
    val accountNumbers: List<String> = emptyList(),
    val statuses: List<String> = emptyList(),
    val cardOwnerId: String,
    val accountOwnerId: String
)