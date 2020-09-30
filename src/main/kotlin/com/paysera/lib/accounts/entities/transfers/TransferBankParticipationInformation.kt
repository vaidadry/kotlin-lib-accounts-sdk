package com.paysera.lib.accounts.entities.transfers

data class TransferBankParticipationInformation(
    val sepaParticipant: Boolean = false,
    val sepaInstantParticipant: Boolean = false,
    val target2Participant: Boolean = false
)