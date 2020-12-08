package com.paysera.lib.accounts.entities.informationRequests

data class PSInformationRequestQuestion(
    val id: String,
    val question: String,
    val inputType: String,
    val required: Boolean,
    val answer: String?
)