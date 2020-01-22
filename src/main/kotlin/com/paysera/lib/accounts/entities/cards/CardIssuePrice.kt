package com.paysera.lib.accounts.entities.cards

import org.joda.money.Money

data class CardIssuePrice(
    val price: Money,
    val country: String,
    val clientType: String
)
