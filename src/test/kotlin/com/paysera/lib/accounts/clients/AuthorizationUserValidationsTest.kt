package com.paysera.lib.accounts.clients

import com.paysera.lib.accounts.entities.authorizations.AuthorizationUserValidationRequest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import runCatchingBlocking

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class AuthorizationUserValidationsTest : BaseTest() {

    private val testUserIds = listOf(
        "-1123123", // not existing
        "-1123534", // not existing
        "1866012",  // lukas sivickas
        "6720691", // john appleseed
        "1419770", // vg@evp.lt
        "8134259" // aga lala
    )

    @Test
    fun getAuthorizationUserValidations() {
        val response = apiClient.getAuthorizationUserValidations(AuthorizationUserValidationRequest(testUserIds)).runCatchingBlocking()
        assert(response.isSuccess)
        val result = response.getOrNull()
        assert(result != null)
        assert(result?.items?.find { it.userId == "1866012" }?.valid == true)
        assert(result?.items?.find { it.userId == "1419770" }?.valid == false)
    }
}