package com.paysera.lib.accounts.clients

import com.paysera.lib.accounts.entities.authorizations.AuthorizationFilter
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import runCatchingBlocking

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class GetAuthorizationTest : BaseTest() {

    val testAccountNumber = "EVP...."

    @Test
    fun getAuthorizations() {
        val result = apiClient.getAuthorizations(AuthorizationFilter(accountNumbers = listOf(testAccountNumber))).runCatchingBlocking()
        assert(result.isSuccess)
    }
}