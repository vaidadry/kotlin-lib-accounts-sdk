package com.paysera.lib.accounts.entities.preciousMetals.requests

data class BullionSpreadPercentageRequest(
    val accountNumber: String,
    val fromCurrency: String,
    val toCurrency: String,
    val toAmount: String
)