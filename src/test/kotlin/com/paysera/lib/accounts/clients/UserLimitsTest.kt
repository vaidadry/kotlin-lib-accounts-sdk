package com.paysera.lib.accounts.clients

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import runCatchingBlocking

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class UserLimitsTest : BaseTest() {

    private val testUserId = 1

    @Test
    fun getUserSigningLimits() {
        val response = apiClient.getUserSigningLimits(testUserId).runCatchingBlocking()
        assert(response.isSuccess)
        val limits = response.getOrNull()
        assert(limits != null)
        assert(limits?.defaultSigningLimits != null)
        assert(limits?.maximumSigningLimits != null)
    }
}