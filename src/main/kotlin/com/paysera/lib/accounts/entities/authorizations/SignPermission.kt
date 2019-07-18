package com.paysera.lib.accounts.entities.authorizations

import org.joda.money.Money

data class SignPermission(
    val level: String,
    val dayLimit: Money?,
    val monthLimit: Money?,
    val yearLimit: Money?,
    val forAutomaticTransfers: Boolean
)