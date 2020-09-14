package com.paysera.lib.accounts.entities.preciousMetals

import org.joda.money.Money

data class Bullion(
    val hash: String,
    val type: String,
    val identifier: String,
    val amount: Money
)