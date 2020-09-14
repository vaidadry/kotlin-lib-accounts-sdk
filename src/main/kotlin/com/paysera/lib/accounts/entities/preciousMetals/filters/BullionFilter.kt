package com.paysera.lib.accounts.entities.preciousMetals.filters

import com.paysera.lib.common.entities.BaseFilter

data class BullionFilter(
    val accountNumber: String
) : BaseFilter()