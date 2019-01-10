package com.paysera.lib.accounts.entities.transfers

import com.google.gson.annotations.JsonAdapter
import com.paysera.lib.accounts.serializers.DateUnixTimestampSecondsAdapter
import com.paysera.lib.accounts.serializers.TransferNotificationDeserializer
import org.joda.money.Money
import java.util.*

class Transfer {
    companion object {
        val URGENCY_STANDARD = "standard"
        val URGENCY_URGENT = "urgent"
    }

    var id: String? = null
    var status: String? = null
    var amount: Money? = null
    var beneficiary: TransferBeneficiary? = null
    var payer: TransferPayer? = null
    var purpose: TransferPurpose? = null
    var cancelable: Boolean? = null
    var initiator: TransferInitiator? = null
    @JsonAdapter(DateUnixTimestampSecondsAdapter::class)
    var createdAt: Date? = null
    @JsonAdapter(DateUnixTimestampSecondsAdapter::class)
    var performAt: Date? = null
    @JsonAdapter(DateUnixTimestampSecondsAdapter::class)
    var performedAt: Date? = null
    var autoCurrencyConvert: Boolean? = null
    var autoChargeRelatedCard: Boolean? = null
    var urgency: String? = null
    var outCommission: Money? = null
    var failureStatus: FailureStatus? = null
    var password: TransferPassword? = null
    @JsonAdapter(TransferNotificationDeserializer::class)
    var notifications: List<TransferNotification>? = null
    var allowedToCancel: Boolean? = null
}
