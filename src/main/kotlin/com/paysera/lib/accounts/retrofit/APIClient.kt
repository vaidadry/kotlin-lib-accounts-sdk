package com.paysera.lib.accounts.retrofit

import com.paysera.lib.accounts.entities.*
import com.paysera.lib.accounts.entities.authorizations.Authorization
import com.paysera.lib.accounts.entities.authorizations.CreateAuthorizationRequest
import com.paysera.lib.accounts.entities.cards.*
import com.paysera.lib.accounts.entities.common.MetadataAwareResponse
import com.paysera.lib.accounts.entities.transfers.Transfer
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface APIClient {
    @POST("account/rest/v1/users/{userId}/accounts")
    fun createAccount(@Path("userId") userId: String): Single<Account>

    @PUT("account/rest/v1/accounts/{accountNumber}/activate")
    fun activateAccount(@Path("accountNumber") accountNumber: String): Single<Account>

    @PUT("account/rest/v1/accounts/{accountNumber}/deactivate")
    fun deactivateAccount(@Path("accountNumber") accountNumber: String): Single<Account>

    @PUT("account/rest/v1/accounts/{accountNumber}/descriptions")
    fun setDefaultAccountDescription(
        @Path("accountNumber") accountNumber: String,
        @Body request: SetDefaultAccountDescriptionRequest
    ): Single<Response<Unit>>

    @GET("questionnaire/rest/v1/user/{userId}/questionnaire")
    fun getLastUserQuestionnaire(
        @Path("userId") userId: Int
    ): Single<Questionnaire>

    @GET("transfer/rest/v1/transfers/{id}")
    fun getTransfer(
        @Path("id") id: String
    ): Single<Transfer>

    @GET("account/rest/v1/accounts/{accountNumber}/full-balance")
    fun getFullBalances(
        @Path("accountNumber") accountNumber: String,
        @Query("show_historical_currencies") showHistoricalCurrencies: Int
    ): Single<List<Balance>>

    @GET("transfer/rest/v1/bank-information/{iban}")
    fun getIbanInformation(@Path("iban") iban: String): Single<IbanInformation>

    @GET("transfer/rest/v1/categorized-account-numbers")
    fun getCategorizedAccountNumbers(
        @Query("categories[]") categories: List<String>
    ): Single<List<CategorizedAccountNumbers>>

    @GET("transfer/rest/v1/purpose-codes")
    fun getTransferPurposeCodes(): Single<MetadataAwareResponse<String>>

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
    fun cancelCard(@Path("id") id: String): Single<Card>

    @GET("issued-payment-card/v1/accounts/{accountNumber}/card-limit")
    fun getCardLimit(@Path("accountNumber") accountNumber: String): Single<CardLimit>

    @PUT("issued-payment-card/v1/accounts/{accountNumber}/card-limit")
    fun setCardLimit(@Path("accountNumber") accountNumber: String, @Body cardLimit: CardLimit): Single<CardLimit>

    @GET("issued-payment-card/v1/accounts/{accountNumber}/shipping-address")
    fun getCardShippingAddress(@Path("accountNumber") accountNumber: String): Single<CardShippingAddress>

    @GET("issued-payment-card/v1/accounts/{accountNumber}/card-order-restriction")
    fun getCardOrderRestriction(@Path("accountNumber") accountNumber: String): Single<CardOrderRestriction>

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

    @GET("issued-payment-card/v1/card-delivery-countries")
    fun getCardDeliveryCountries(
        @Query("offset") offset: Int?,
        @Query("limit") limit: Int?
    ): Single<MetadataAwareResponse<String>>

    @GET("client-allowance/rest/v1/client-allowances/can-order-card")
    fun canOrderCard(@Query("user_id") userId: String): Single<ClientAllowance>

    @GET("client-allowance/rest/v1/client-allowances/can-fill-questionnaire")
    fun canFillQuestionnare(@Query("user_id") userId: String): Single<ClientAllowance>

    @GET("permission/rest/v1/authorizations")
    fun getAuthorizations(
        @Query("account_numbers[]") accountNumbers: List<String>,
        @Query("valid_from") validFrom: Long?,
        @Query("valid_to") validTo: Long?,
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?,
        @Query("order_by") orderBy: String?,
        @Query("order_direction") orderDirection: String?,
        @Query("replaced_authorization_ids") replacedAuthorizationIds: List<String>?
    ): Single<List<Authorization>>

    @POST("permission/rest/v1/authorizations")
    fun createAuthorization(@Body authorization: CreateAuthorizationRequest): Single<List<Authorization>>

    @DELETE("permission/rest/v1/authorizations/{authorizationId}")
    fun deleteAuthorization(@Path("authorizationId") authorizationId: String): Completable

    @PUT("permission/rest/v1/authorizations/{authorizationId}")
    fun updateAuthorization(
        @Path("authorizationId") authorizationId: String,
        @Body authorization: CreateAuthorizationRequest
    ): Single<List<Authorization>>

    @DELETE("permission/rest/v1/authorizations/{authorizationId}/users/{userId}")
    fun revokeUserAuthorization(
        @Path("authorizationId") authorizationId: String,
        @Path("userId") userId: Int
    ): Completable
}