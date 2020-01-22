package com.paysera.lib.accounts.clients

import com.paysera.lib.accounts.entities.Account
import com.paysera.lib.accounts.entities.CardLimit
import com.paysera.lib.accounts.entities.SetDefaultAccountDescriptionRequest
import com.paysera.lib.accounts.entities.authorizations.AuthorizationFilter
import com.paysera.lib.accounts.entities.authorizations.CreateAuthorizationRequest
import com.paysera.lib.accounts.entities.cards.*
import com.paysera.lib.accounts.entities.common.BaseFilter
import com.paysera.lib.accounts.retrofit.NetworkApiClient
import com.paysera.lib.common.retrofit.ApiRequestManager
import com.paysera.lib.common.retrofit.BaseApiClient
import kotlinx.coroutines.Deferred

class AccountsApiClient(
    private val networkApiClient: NetworkApiClient,
    apiRequestManager: ApiRequestManager
) : BaseApiClient(apiRequestManager) {

    fun createAccount(userId: Int): Deferred<Account> {
        return networkApiClient.createAccount(userId)
    }

    fun setDefaultAccountDescription(accountNumber: String, description: String) =
        networkApiClient.setDefaultAccountDescription(
            accountNumber,
            SetDefaultAccountDescriptionRequest(description)
        )

    fun activateAccount(accountNumber: String) =
        networkApiClient.activateAccount(
            accountNumber
        )

    fun deactivateAccount(accountNumber: String) =
        networkApiClient.deactivateAccount(
            accountNumber
        )

    fun getIbanInformation(iban: String) =
        networkApiClient.getIbanInformation(
            iban
        )

    fun getFullBalance(accountNumber: String, showHistoricalCurrencies: Boolean = false) =
        networkApiClient.getFullBalances(
            accountNumber,
            if (showHistoricalCurrencies) 1 else 0
        )

    fun getCategorizedAccountNumbers(filter: CategorizedAccountNumbersFilter) =
        networkApiClient.getCategorizedAccountNumbers(
            filter.categories
        )

    fun getTransferPurposeCodes() =
        networkApiClient.getTransferPurposeCodes()

    fun getCards(cardsFilter: CardsFilter) =
        networkApiClient.getCards(
            cardsFilter.accountNumbers,
            cardsFilter.statuses,
            cardsFilter.accountOwnerId,
            cardsFilter.cardOwnerId
        )

    fun getPaymentCardDesigns(paymentCardDesignFilter: PaymentCardDesignFilter) =
        networkApiClient.getPaymentCardDesigns(
            paymentCardDesignFilter.accountOwnerId,
            paymentCardDesignFilter.clientType
        )

    fun createCard(card: CreatePaymentCardRequest) =
        networkApiClient.createCard(
            card
        )

    fun activateCard(cardId: String) =
        networkApiClient.activateCard(
            cardId
        )

    fun deactivateCard(cardId: String) =
        networkApiClient.deactivateCard(
            cardId
        )

    fun enableCard(cardId: String) =
        networkApiClient.enableCard(
            cardId
        )

    fun cancelCard(cardId: String) =
        networkApiClient.cancelCard(
            cardId
        )

    fun getCardLimit(accountNumber: String) =
        networkApiClient.getCardLimit(
            accountNumber
        )

    fun setCardLimit(accountNumber: String, cardLimit: CardLimit) =
        networkApiClient.setCardLimit(
            accountNumber,
            cardLimit
        )

    fun getCardPin(cardId: String, cardCvv2: CardCvv2) =
        networkApiClient.getCardPin(
            cardId,
            cardCvv2
        )

    fun getCardShippingAddress(accountNumber: String) =
        networkApiClient.getCardShippingAddress(
            accountNumber
        )

    fun getCardOrderRestriction(accountNumber: String) =
        networkApiClient.getCardOrderRestriction(
            accountNumber
        )

    fun getCardDeliveryPrices(country: String) =
        networkApiClient.getCardDeliveryPrices(
            country
        )

    fun getCardIssuePrice(country: String, clientType: String, cardOwnerId: Int) =
        networkApiClient.getCardIssuePrice(
            country,
            clientType,
            cardOwnerId
        )

    fun getCardDeliveryDate(country: String, deliveryType: String) =
        networkApiClient.getCardDeliveryDate(
            country,
            deliveryType
        )

    fun getCardDeliveryCountries(filter: BaseFilter) =
        networkApiClient.getCardDeliveryCountries(
            offset = filter.offset,
            limit = filter.limit
        )

    fun getLastUserQuestionnaire(userId: Int) =
        networkApiClient.getLastUserQuestionnaire(
            userId
        )

    fun getTransfer(id: String) =
        networkApiClient.getTransfer(
            id
        )

    fun canOrderCard(userId: Int) =
        networkApiClient.canOrderCard(
            userId
        )

    fun canFillQuestionnaire(userId: Int) =
        networkApiClient.canFillQuestionnaire(
            userId
        )

    fun createAuthorization(authorization: CreateAuthorizationRequest) =
        networkApiClient.createAuthorization(
            authorization
        )

    fun getAuthorizations(filter: AuthorizationFilter) =
        networkApiClient.getAuthorizations(
            filter.accountNumbers,
            filter.validFrom?.time,
            filter.validTo?.time,
            filter.limit,
            filter.offset,
            filter.orderBy,
            filter.orderDirectionBy,
            filter.replacedAuthorizationIds
        )

    fun updateAuthorization(authorizationId: String, authorization: CreateAuthorizationRequest) =
        networkApiClient.updateAuthorization(
            authorizationId,
            authorization
        )

    fun deleteAuthorization(authorizationId: String) =
        networkApiClient.deleteAuthorization(
            authorizationId
        )

    fun revokeUserAuthorization(authorizationId: String, userId: Int) =
        networkApiClient.revokeUserAuthorization(
            authorizationId,
            userId
        )
}