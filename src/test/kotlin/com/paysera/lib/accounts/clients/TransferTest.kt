package com.paysera.lib.accounts.clients

import com.paysera.lib.accounts.entities.cards.CategorizedAccountNumbersFilter
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import runCatchingBlocking

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class TransferTest : BaseTest() {

    private val testTransferId = "abc"
    private val testIban = "LT....."

    @Test
    fun getTransfer() {
        val response = apiClient.getTransfer(testTransferId).runCatchingBlocking()
        assert(response.isSuccess)
    }

    @Test
    fun getIbanInformation() {
        val response = apiClient.getIbanInformation(testIban).runCatchingBlocking()
        assert(response.isSuccess)
        assert(response.getOrNull() != null)
    }

    @Test
    fun getCategorizedAccountNumbers() {
        val response = apiClient.getCategorizedAccountNumbers(
            CategorizedAccountNumbersFilter()
        ).runCatchingBlocking()
        assert(response.isSuccess)
    }

    @Test
    fun getTransferPurposeCodes() {
        val response = apiClient.getTransferPurposeCodes().runCatchingBlocking()
        assert(response.isSuccess)
    }
}