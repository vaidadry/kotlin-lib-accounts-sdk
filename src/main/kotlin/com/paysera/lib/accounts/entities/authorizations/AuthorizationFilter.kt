package com.paysera.lib.accounts.entities.authorizations

import java.util.*

data class AuthorizationFilter(
    val accountNumbers: List<String>,
    var validFrom: Date? = null,
    var validTo: Date? = null,
    var limit: Int? = null,
    var offset: Int? = null,
    var orderBy: String? = null,
    var orderDirectionBy: String? = null,
    var replacedAuthorizationIds: List<String>? = null
)