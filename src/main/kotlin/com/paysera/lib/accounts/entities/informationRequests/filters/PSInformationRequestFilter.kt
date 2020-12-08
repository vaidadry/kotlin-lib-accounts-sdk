package com.paysera.lib.accounts.entities.informationRequests.filters

import com.paysera.lib.common.entities.BaseFilter

class PSInformationRequestFilter(
    val transferId: String? = null,
    val accountNumbers: List<String>? = null,
    val status: String? = null,
    val internalCommentRequired: Boolean? = null,
    limit: Int? = null,
    offset: Int? = null,
    orderBy: String? = null,
    orderDirection: String? = null,
    after: String? = null,
    before: String? = null
) : BaseFilter(
    limit = limit,
    offset = offset,
    orderBy = orderBy,
    orderDirection = orderDirection,
    after = after,
    before = before
)