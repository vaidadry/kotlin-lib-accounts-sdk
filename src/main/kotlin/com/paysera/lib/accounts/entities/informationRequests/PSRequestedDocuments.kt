package com.paysera.lib.accounts.entities.informationRequests

data class PSRequestedDocuments(
    val instructions: String,
    val documents: List<String>
)