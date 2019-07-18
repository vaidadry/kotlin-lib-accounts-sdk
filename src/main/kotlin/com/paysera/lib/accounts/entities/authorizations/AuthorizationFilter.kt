package com.paysera.lib.accounts.entities.authorizations

data class AuthorizationFilter(
    val accountNumbers: List<String>,
    var validFrom: Long? = null,
    var validTo: Long? = null,
    var limit: Int? = null,
    var offset: Int? = null,
    var orderBy: String? = null,
    var orderDirectionBy: String? = null,
    var replacedAuthorizationIds: List<String>? = null
)