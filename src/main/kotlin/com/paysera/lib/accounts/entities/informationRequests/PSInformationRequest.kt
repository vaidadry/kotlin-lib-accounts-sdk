package com.paysera.lib.accounts.entities.informationRequests

import java.util.*

data class PSInformationRequest(
    val id: String,
    val transferId: String,
    val transferInformation: PSTransferInformation,
    val internalComment: String?,
    val comment: String?,
    val status: String,
    val createdAt: Date?,
    val answeredAt: Date?,
    val notifyClientBy: List<String>?,
    val questions: List<PSInformationRequestQuestion>,
    val requestedFrom: String,
    val files: List<PSInformationRequestUploadedFile>?,
    val requestedDocuments: PSRequestedDocuments
)