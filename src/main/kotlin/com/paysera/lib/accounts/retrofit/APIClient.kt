package com.paysera.lib.accounts.retrofit

import com.paysera.lib.accounts.entities.Balance
import com.paysera.lib.accounts.entities.IbanInformation
import com.paysera.lib.accounts.entities.cards.Card
import io.reactivex.Observable
import retrofit2.http.*

interface APIClient {

    @GET("account/rest/v1/accounts/{accountNumber}/full-balance")
    fun getFullBalances(@Path("accountNumber") accountNumber: String): Observable<List<Balance>>

    @GET("transfer/rest/v1/swift/{iban}")
    fun getIbanInformation(@Path("iban") iban: String): Observable<IbanInformation>

    @GET("payment-card/v1/cards")
    fun getCards(
        @Query("account_numbers[]") accountNumbers: List<String>,
        @Query("statuses[]") statuses: List<String>,
        @Query("account_owner_id") accountOwnerId: String,
        @Query("card_owner_id") cardOwnerId: String
    ): Observable<List<Card>>

    @POST("payment-card/v1/cards")
    fun createCard(@Body card: Card): Observable<Card>

    @PUT("payment-card/v1/cards/{id}/activate")
    fun activateCard(@Path("id") id: String): Observable<Card>

}