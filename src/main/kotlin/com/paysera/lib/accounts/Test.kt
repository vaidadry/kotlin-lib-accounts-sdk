package com.paysera.lib.accounts

import com.paysera.lib.accounts.clients.AccountsApiClient
import com.paysera.lib.accounts.entities.AccountsApiCredentials
import com.paysera.lib.accounts.interfaces.TokenRefresherInterface
import com.paysera.lib.accounts.retrofit.APIClient
import com.paysera.lib.accounts.retrofit.RetrofitConfigurator
import io.reactivex.Single

fun main() {

    val accessToken = ""    //  your access token
    val credentials = AccountsApiCredentials(accessToken)

    val tokenRefresher = object : TokenRefresherInterface {
        override fun isRefreshing(): Boolean {
            println("isRefreshing() invoked")
            return false
        }

        override fun refreshToken(): Single<Any> {
            println("refreshToken() invoked")
            return Single.just(Any())
        }
    }

    val retrofitClient = RetrofitConfigurator(credentials).createRetrofit()
    val apiClient = AccountsApiClient(retrofitClient.create(APIClient::class.java), tokenRefresher)

    apiClient.getCardDeliveryCountries().subscribe({ response ->
        println(response.items)
    }, { error ->
        println(error.localizedMessage)
    })

    Thread.sleep(5000)
}