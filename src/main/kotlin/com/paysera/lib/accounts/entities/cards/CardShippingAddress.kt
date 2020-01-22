package com.paysera.lib.accounts.entities.cards

data class CardShippingAddress(
    val postalCode: String,
    val address: String,
    val city: String,
    val country: String
)