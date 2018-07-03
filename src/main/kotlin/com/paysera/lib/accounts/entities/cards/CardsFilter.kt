package com.paysera.lib.accounts.entities.cards

class CardsFilter(
    var accountNumbers: List<String> = emptyList(),
    var statuses: List<String> = emptyList(),
    var cardOwnerId: String? = null,
    var accountOwnerId: String? = null
)