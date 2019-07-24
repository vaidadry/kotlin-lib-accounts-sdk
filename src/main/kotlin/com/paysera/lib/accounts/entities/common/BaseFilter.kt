package com.paysera.lib.accounts.entities.common

open class BaseFilter(
    var offset: Int? = null,
    var limit: Int? = null,
    var orderBy: String? = null,
    var orderDirection: String? = null,
    var after: String? = null,
    var before: String? = null
)