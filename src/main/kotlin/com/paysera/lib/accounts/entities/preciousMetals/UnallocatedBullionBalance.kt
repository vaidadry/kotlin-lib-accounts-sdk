package com.paysera.lib.accounts.entities.preciousMetals

import org.joda.money.Money

data class UnallocatedBullionBalance(
    val accountNumber: String,
    val amount: Money
)