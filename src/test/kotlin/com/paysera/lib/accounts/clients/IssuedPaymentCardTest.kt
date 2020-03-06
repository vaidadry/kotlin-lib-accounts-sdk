package com.paysera.lib.accounts.clients

import com.paysera.lib.accounts.entities.CardLimit
import com.paysera.lib.accounts.entities.cards.CardsFilter
import com.paysera.lib.accounts.entities.cards.PaymentCardDesignFilter
import com.paysera.lib.common.entities.BaseFilter
import org.joda.money.CurrencyUnit
import org.joda.money.Money
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import runCatchingBlocking
import java.math.BigDecimal

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class IssuedPaymentCardTest : BaseTest() {

    private val testCardAccountNumber = "EVP...."
    private val testUserId = -1 // your user id
    private val testAccountOwnerId = -1
    private val testCountry = "lt"

    @Test
    fun getCards() {
        val response = apiClient.getCards(
            CardsFilter(listOf(testCardAccountNumber))
        ).runCatchingBlocking()
        assert(response.isSuccess)
    }

    @Test
    fun getPaymentCardDesigns() {
        val response = apiClient.getPaymentCardDesigns(
            PaymentCardDesignFilter(testUserId, "natural")
        ).runCatchingBlocking()
        assert(response.isSuccess)
    }

    @Test
    fun getCardLimit() {
        val response = apiClient.getCardLimit(
            testCardAccountNumber
        ).runCatchingBlocking()
        assert(response.isSuccess)
    }

    @Test
    fun setCardLimit() {
        val response = apiClient.setCardLimit(
            testCardAccountNumber,
            CardLimit(Money.of(CurrencyUnit.EUR, BigDecimal("500")))
        ).runCatchingBlocking()
        assert(response.isSuccess)
    }

    @Test
    fun getCardShippingAddress() {
        val response = apiClient.getCardShippingAddress(
            testCardAccountNumber
        ).runCatchingBlocking()
        assert(response.isSuccess)
    }

    @Test
    fun getCardOrderRestriction() {
        val response = apiClient.getCardOrderRestriction(
            testCardAccountNumber
        ).runCatchingBlocking()
        assert(response.isSuccess)
    }

    @Test
    fun getCardDeliveryPrices() {
        val response = apiClient.getCardDeliveryPrices(
            testCountry
        ).runCatchingBlocking()
        assert(response.isSuccess)
    }

    @Test
    fun getCardIssuePrice() {
        val response = apiClient.getCardIssuePrice(
            testAccountOwnerId,
            testUserId
        ).runCatchingBlocking()
        assert(response.isSuccess)
    }

    @Test
    fun getCardDeliveryDate() {
        val response = apiClient.getCardDeliveryDate(
            testCountry,
            "regular"
        ).runCatchingBlocking()
        assert(response.isSuccess)
    }

    @Test
    fun getCardDeliveryCountries() {
        val response = apiClient.getCardDeliveryCountries(
            BaseFilter()
        ).runCatchingBlocking()
        assert(response.isSuccess)
    }
}