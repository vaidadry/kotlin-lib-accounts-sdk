package com.paysera.lib.accounts.clients

import com.paysera.lib.accounts.entities.cards.CardShippingAddress
import com.paysera.lib.accounts.entities.cards.PaymentCardDelivery
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import runCatchingBlocking

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class CardDeliveryAddressTest : BaseTest() {

    private val testCardAccountNumber = "EVP.."

    @Test
    fun getPaymentCardDeliveryPreference() {
        val response = apiClient.getPaymentCardDeliveryPreference(testCardAccountNumber).runCatchingBlocking()
        assert(response.isSuccess)
    }

    @Test
    fun setPaymentCardDeliveryPreference() {
        val response = apiClient.setPaymentCardDeliveryPreference(
            testCardAccountNumber,
            PaymentCardDelivery(
                6720691,
                CardShippingAddress("08426","pilaites pr.16","Vilnius", "LT"),
                "regular"
            )
        ).runCatchingBlocking()
        assert(response.isSuccess)
    }

    @Test
    fun getPaymentCardExpiringOrderRestriction() {
        val response = apiClient.getPaymentCardExpiringOrderRestriction(testCardAccountNumber).runCatchingBlocking()
        assert(response.isSuccess)
    }
}