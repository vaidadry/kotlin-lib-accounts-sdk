package com.paysera.lib.accounts.retrofit

import com.paysera.lib.accounts.entities.Balance
import com.paysera.lib.accounts.entities.IbanInformation
import com.paysera.lib.accounts.entities.CardLimit
import com.paysera.lib.accounts.entities.cards.*
import io.reactivex.Single
import retrofit2.http.*

interface APIClient {

    @GET("account/rest/v1/accounts/{accountNumber}/full-balance")
    fun getFullBalances(
        @Path("accountNumber") accountNumber: String,
        @Query("show_historical_currencies") showHistoricalCurrencies: Int
    ): Single<List<Balance>>


    @GET("transfer/rest/v1/bank-information/{iban}")
    fun getIbanInformation(@Path("iban") iban: String): Single<IbanInformation>

    @GET("issued-payment-card/v1/cards")
    fun getCards(
        @Query("account_numbers[]") accountNumbers: List<String>,
        @Query("statuses[]") statuses: List<String>,
        @Query("account_owner_id") accountOwnerId: String?,
        @Query("card_owner_id") cardOwnerId: String?
    ): Single<List<Card>>

    @POST("issued-payment-card/v1/cards")
    fun createCard(@Body card: CreatePaymentCardRequest): Single<Card>

    @PUT("issued-payment-card/v1/cards/{id}/activate")
    fun activateCard(@Path("id") id: String): Single<Card>

    @PUT("issued-payment-card/v1/cards/{id}/deactivate")
    fun deactivateCard(@Path("id") id: String): Single<Card>

    @PUT("issued-payment-card/v1/cards/{id}/enable")
    fun enableCard(@Path("id") id: String): Single<Card>

    @PUT("issued-payment-card/v1/cards/{id}/cancel")
    fun cancelCard(@Path("id") id:String): Single<Card>

    @GET("issued-payment-card/v1/accounts/{accountNumber}/card-limit")
    fun getCardLimit(@Path("accountNumber") accountNumber: String): Single<CardLimit>

    @PUT("issued-payment-card/v1/accounts/{accountNumber}/card-limit")
    fun setCardLimit(@Path("accountNumber") accountNumber: String, @Body cardLimit: CardLimit): Single<CardLimit>

    @GET("issued-payment-card/v1/accounts/{accountNumber}/shipping-address")
    fun getCardShippingAddress(@Path("accountNumber") accountNumber: String): Single<CardShippingAddress>

    @PUT("issued-payment-card/v1/cards/{id}/pin")
    fun getCardPin(@Path("id") cardId: String, @Body cardCvv2: CardCvv2): Single<CardPin>

    @GET("issued-payment-card/v1/card-delivery-prices/{country}")
    fun getCardDeliveryPrices(@Path("country") country: String): Single<List<CardDeliveryPrice>>

    @GET("issued-payment-card/v1/card-issue-price/{country}/{clientType}/{cardOwnerId}")
    fun getCardIssuePrice(
        @Path("country") country: String,
        @Path("clientType") clientType: String,
        @Path("cardOwnerId") cardOwnerId: String
    ): Single<CardIssuePrice>

    @GET("issued-payment-card/v1/card-delivery-date")
    fun getCardDeliveryDate(
        @Query("country") country: String,
        @Query("delivery_type") deliveryType: String
    ): Single<CardDeliveryDate>
}
