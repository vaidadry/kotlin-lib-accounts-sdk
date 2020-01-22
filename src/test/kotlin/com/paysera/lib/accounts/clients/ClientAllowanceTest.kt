package com.paysera.lib.accounts.clients

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import runCatchingBlocking

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class ClientAllowanceTest : BaseTest() {

    private val userId = -1 // your userId

    @Test
    fun canOrderCard() {
        val response = apiClient.canOrderCard(userId).runCatchingBlocking()
        assert(response.isSuccess)
        assert(response.getOrNull()?.allowed != null)
    }

    @Test
    fun canFillQuestionnaire() {
        val response = apiClient.canFillQuestionnaire(userId).runCatchingBlocking()
        assert(response.isSuccess)
        assert(response.getOrNull()?.allowed != null)
    }
}