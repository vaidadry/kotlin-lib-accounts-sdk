package com.paysera.lib.accounts.entities.cards

import org.joda.money.Money

class Card(
    val id: Int,
    val cardOwnerId: Int,
    val accountOwnerId: Int,
    val nameOnCard: String,
    val maskedNumber: String,
    val accountNumber: String,
    val shippingAddress: ShippingAddress,
    val chargeInfo: ChargeInfo,
    val status: String,
    val expirationDate: String,
    val orderDate: String,
    val shippingDate: String,
    val main: Boolean,
    val deliveryType: String,
    val deliveryPriceAmount: Money,
    val issuePriceAmount: Money,
    val account: CardAccount
)