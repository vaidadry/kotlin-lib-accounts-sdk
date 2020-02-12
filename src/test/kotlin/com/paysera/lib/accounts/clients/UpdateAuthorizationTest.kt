package com.paysera.lib.accounts.clients

import com.paysera.lib.accounts.entities.authorizations.AuthorizationFilter
import com.paysera.lib.accounts.entities.authorizations.CreateAuthorizationRequest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import runCatchingBlocking

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class UpdateAuthorizationTest : BaseTest() {

    private val testAccountNumber = "EVP..."
    private val testUserIdList = listOf(-1, -1)

    private var authorizationId: String? = null

    override fun setUp() {
        super.setUp()
        val result = apiClient.createAuthorization(CreateAuthorizationRequest(
            accountNumber = testAccountNumber,
            userIds = testUserIdList,
            readPermission = true,
            writePermission = false,
            signPermission = null
        )).runCatchingBlocking()
        assert(result.isSuccess)
        val authorization = result.getOrNull()
        assert(authorization != null)
        authorizationId = authorization?.id
    }

    override fun tearDown() {
        authorizationId?.let {
            apiClient.deleteAuthorization(it).runCatchingBlocking()
        }
        authorizationId = null
        super.tearDown()
    }

    @Test
    fun updateAuthorization() {
        val result = apiClient.updateAuthorization(
            authorizationId!!,
            CreateAuthorizationRequest(
                accountNumber = testAccountNumber,
                userIds = testUserIdList,
                readPermission = true,
                writePermission = true,
                signPermission = null
            )
        ).runCatchingBlocking()
        assert(result.isSuccess)
        val authorization = result.getOrNull()
        assert(authorization != null)
        assert(authorization!!.writePermission)
        authorizationId = authorization.id
    }

    @Test
    fun revokeUserAuthorization() {
        val result = apiClient.revokeUserAuthorization(
            authorizationId!!,
            testUserIdList.first()
        ).runCatchingBlocking()
        assert(result.isSuccess)
        val authorizations = apiClient.getAuthorizations(
            AuthorizationFilter(listOf(testAccountNumber))
        ).runCatchingBlocking().getOrNull()
        authorizations?.items?.firstOrNull { it.id == authorizationId }?.let {
            assert(it.users.size == 1)
        } ?: run {
            assert(false)
        }
    }
}