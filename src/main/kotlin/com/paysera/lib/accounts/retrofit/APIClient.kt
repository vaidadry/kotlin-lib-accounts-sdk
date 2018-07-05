package com.paysera.lib.accounts.retrofit

import com.paysera.lib.accounts.entities.Balance
import com.paysera.lib.accounts.entities.IbanInformation
import com.paysera.lib.accounts.entities.CardLimit
import com.paysera.lib.accounts.entities.cards.Card
import com.paysera.lib.accounts.entities.cards.CardCvv2
import com.paysera.lib.accounts.entities.cards.CardPin
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
        @Query("account_owner_id") accountOwnerId: String?,
        @Query("card_owner_id") cardOwnerId: String?
    ): Observable<List<Card>>

    @POST("payment-card/v1/cards")
    fun createCard(@Body card: Card): Observable<Card>

    @PUT("payment-card/v1/cards/{id}/activate")
    fun activateCard(@Path("id") id: String): Observable<Card>

    @PUT("payment-card/v1/cards/{id}/deactivate")
    fun deactivateCard(@Path("id") id: String): Observable<Card>

    @PUT("payment-card/v1/cards/{id}/cancel")
    fun cancelCard(@Path("id") id:String): Observable<Card>

    @GET("payment-card/v1/accounts/{accountNumber}/card-limit")
    fun getCardLimit(@Path("accountNumber") accountNumber: String): Observable<CardLimit>

    @PUT("payment-card/v1/accounts/{accountNumber}/card-limit")
    fun setCardLimit(@Path("accountNumber") accountNumber: String, @Body cardLimit: CardLimit): Observable<CardLimit>

    @PUT("payment-card/v1/cards/{id}/pin")
    fun getCardPin(@Path("id") cardId: String, @Body cardCvv2: CardCvv2): Observable<CardPin>
}