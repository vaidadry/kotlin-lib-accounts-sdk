package com.paysera.lib.accounts.entities.authorizations

import org.joda.money.Money

data class SignPermission(
    var level: String,
    var dayLimit: Money?,
    var monthLimit: Money?,
    var yearLimit: Money?,
    var forAutomaticTransfers: Boolean
)