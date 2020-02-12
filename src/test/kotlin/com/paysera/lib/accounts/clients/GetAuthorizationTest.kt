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
        val result = apiClient.getAuthorizations(
            AuthorizationFilter(accountNumbers = listOf(testAccountNumber))
        ).runCatchingBlocking()
        assert(result.isSuccess)
    }

    @Test
    fun getAuthorizationsOffset() {
        val result = apiClient.getAuthorizations(
            AuthorizationFilter(accountNumbers = listOf(testAccountNumber), offset = 1)
        ).runCatchingBlocking()
        assert(result.isSuccess)
        assert(result.getOrNull()?.metadata?.offset == 1)
    }

    @Test
    fun getAuthorizationsLimit() {
        val result = apiClient.getAuthorizations(
            AuthorizationFilter(accountNumbers = listOf(testAccountNumber), limit = 1)
        ).runCatchingBlocking()
        assert(result.isSuccess)
        assert(result.getOrNull()?.items?.size == 1)
    }

    @Test
    fun getAuthorizationsFilterAndOffset() {
        val result = apiClient.getAuthorizations(
            AuthorizationFilter(accountNumbers = listOf(testAccountNumber), limit = 1, offset = 1)
        ).runCatchingBlocking()
        assert(result.isSuccess)
        assert(result.getOrNull()?.items?.size == 1)
        assert(result.getOrNull()?.metadata?.offset == 1)
    }
}