package com.paysera.lib.accounts.entities.cards

data class PaymentCardExpiringOrderRestriction(
    val orderingAllowed: Boolean,
    val reason: String
)