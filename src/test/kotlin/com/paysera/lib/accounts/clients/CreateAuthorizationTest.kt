package com.paysera.lib.accounts.clients

import com.paysera.lib.accounts.entities.authorizations.CreateAuthorizationRequest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import runCatchingBlocking

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class CreateAuthorizationTest : BaseTest() {

    private val testAccountNumber = "EVP..."
    private val testUserIdList = listOf(-1, -1)

    private var authorizationId: String? = null

    override fun tearDown() {
        authorizationId?.let {
            apiClient.deleteAuthorization(it).runCatchingBlocking()
        }
        authorizationId = null
        super.tearDown()
    }

    @Test
    fun createAuthorization() {
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
}