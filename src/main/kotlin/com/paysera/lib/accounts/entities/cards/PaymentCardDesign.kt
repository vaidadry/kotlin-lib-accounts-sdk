package com.paysera.lib.accounts.entities.cards

data class PaymentCardDesign(
    val id: Int,
    val name: String,
    val visualType: String,
    val selectorColor: String,
    val priority: Int,
    val active: Boolean,
    val visualUrl: String,
    val visualBackUrl: String
)