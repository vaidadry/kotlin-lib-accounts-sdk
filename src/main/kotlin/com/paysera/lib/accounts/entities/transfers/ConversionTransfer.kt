package com.paysera.lib.accounts.entities.transfers

import org.joda.money.Money
import java.util.*

data class ConversionTransfer(
    val id: String,
    val identifier: Int,
    val status: String,
    val details: String,
    val accountNumber: String,
    val payer: TransferPayer,
    val from: Money,
    val to: Money,
    val type: String,
    val createdAt: Date?,
    val performAt: Date?,
    val allowedToCancel: Boolean
)