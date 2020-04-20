package com.paysera.lib.accounts.entities.authorizations

import org.joda.money.Money

data class UserLimits(
    val defaultSigningLimits: Limit,
    val maximumSigningLimits: Limit
) {
    data class Limit(
        val dayLimit: Money?,
        val monthLimit: Money?,
        val yearLimit: Money?
    )
}