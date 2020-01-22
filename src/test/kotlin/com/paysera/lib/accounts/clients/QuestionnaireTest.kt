package com.paysera.lib.accounts.clients

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import runCatchingBlocking

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class QuestionnaireTest : BaseTest() {

    private val testUserId = -1 // your userId

    @Test
    fun getLastUserQuestionnaire() {
        val response = apiClient.getLastUserQuestionnaire(testUserId).runCatchingBlocking()
        assert(response.isSuccess)
    }
}