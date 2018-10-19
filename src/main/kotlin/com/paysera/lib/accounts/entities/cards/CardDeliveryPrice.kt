package com.paysera.lib.accounts.entities.cards

import org.joda.money.Money

class CardDeliveryPrice(
    val price: Money,
    val country: String,
    val deliveryType: String
)
