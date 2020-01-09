package com.paysera.lib.accounts.entities.cards

import com.paysera.lib.accounts.entities.common.BaseFilter

data class PaymentCardDesignFilter(
    val accountOwnerId: Int?,
    val clientType: String?
) : BaseFilter()