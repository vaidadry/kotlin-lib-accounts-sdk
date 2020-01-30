package com.paysera.lib.accounts.entities.transfers

import com.paysera.lib.common.entities.BaseFilter

data class ConversionTransferFilter(
    val accountNumberList: List<String>,
    val statuses: List<String>
) : BaseFilter()