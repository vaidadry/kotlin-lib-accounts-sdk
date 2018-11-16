package com.paysera.lib.accounts.entities.cards

class CreatePaymentCardRequest (
    var cardOwnerId: Int? = null,
    var shippingAddress: CardShippingAddress? = null,
    var deliveryType: String? = null,
    var accountNumber: String? = null,
    var accountOwnerId: Int? = null,
    var chargeInfo: ChargeInfo? = null
)