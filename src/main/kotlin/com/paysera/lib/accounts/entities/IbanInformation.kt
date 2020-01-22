package com.paysera.lib.accounts.entities

data class IbanInformation(
    val swift: String,
    val bankName: String,
    val branchCode: String?,
    val bankCode: String,
    val sepaParticipant: Boolean = false,
    val sepaInstantParticipant: Boolean = false,
    val country: String
)