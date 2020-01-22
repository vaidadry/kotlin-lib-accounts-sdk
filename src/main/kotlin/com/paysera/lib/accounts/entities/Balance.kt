package com.paysera.lib.accounts.entities

import org.joda.money.Money

data class Balance(
    val atDisposal: Money,
    val reserved: Money
)