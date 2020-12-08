package com.paysera.lib.accounts.entities.informationRequests.enums

enum class PSInformationRequestStatus(val value: String) {
    WAITING_FOR_ANSWER("waiting_for_answer"),
    ANSWERED("answered"),
    COMPLETED("completed"),
    CANCELED("canceled")
}