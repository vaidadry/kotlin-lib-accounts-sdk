package com.paysera.lib.accounts.entities.cards

data class CardOrderRestriction(
    val orderingAllowed: Boolean,
    val reason: String?
)