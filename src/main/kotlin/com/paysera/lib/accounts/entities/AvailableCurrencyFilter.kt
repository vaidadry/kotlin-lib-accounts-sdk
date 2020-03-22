package com.paysera.lib.accounts.entities

import com.paysera.lib.common.entities.BaseFilter

class AvailableCurrencyFilter(
    val userId: Int,
    offset: Int? = null,
    limit: Int? = null
) : BaseFilter(
    offset = offset,
    limit = limit
)