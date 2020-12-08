package com.paysera.lib.accounts.clients

import com.paysera.lib.accounts.entities.Account
import com.paysera.lib.accounts.entities.AvailableCurrencyFilter
import com.paysera.lib.accounts.entities.CardLimit
import com.paysera.lib.accounts.entities.SetDefaultAccountDescriptionRequest
import com.paysera.lib.accounts.entities.authorizations.Authorization
import com.paysera.lib.accounts.entities.authorizations.AuthorizationFilter
import com.paysera.lib.accounts.entities.authorizations.AuthorizationUserValidationRequest
import com.paysera.lib.accounts.entities.authorizations.CreateAuthorizationRequest
import com.paysera.lib.accounts.entities.cards.*
import com.paysera.lib.accounts.entities.informationRequests.PSInformationRequest
import com.paysera.lib.accounts.entities.informationRequests.PSInformationRequestAnswers
import com.paysera.lib.accounts.entities.informationRequests.PSInformationRequestFile
import com.paysera.lib.accounts.entities.informationRequests.filters.PSInformationRequestFilter
import com.paysera.lib.accounts.entities.preciousMetals.filters.BullionFilter
import com.paysera.lib.accounts.entities.preciousMetals.requests.BullionSpreadPercentageRequest
import com.paysera.lib.accounts.entities.preciousMetals.requests.BuyBullionItemRequest
import com.paysera.lib.accounts.entities.preciousMetals.requests.SellBullionItemRequest
import com.paysera.lib.accounts.entities.transfers.ConversionTransferFilter
import com.paysera.lib.accounts.retrofit.NetworkApiClient
import com.paysera.lib.common.entities.BaseFilter
import com.paysera.lib.common.entities.MetadataAwareResponse
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

    fun getIbanInformation(iban: String, currency: String) =
        networkApiClient.getIbanInformation(
            iban,
            currency
        )

    fun getFullBalance(accountNumber: String, showHistoricalCurrencies: Boolean = false) =
        networkApiClient.getFullBalances(
            accountNumber,
            if (showHistoricalCurrencies) 1 else 0
        )

    fun getAvailableCurrencies(availableCurrencyFilter: AvailableCurrencyFilter) =
        networkApiClient.getAvailableCurrencies(
            availableCurrencyFilter.userId,
            availableCurrencyFilter.offset,
            availableCurrencyFilter.limit
        )

    fun getCategorizedAccountNumbers(filter: CategorizedAccountNumbersFilter) =
        networkApiClient.getCategorizedAccountNumbers(
            filter.categories
        )

    fun getTransferPurposeCodes() =
        networkApiClient.getTransferPurposeCodes()

    fun getConversionTransfers(filter: ConversionTransferFilter) =
        networkApiClient.getConversionTransfers(
            filter.accountNumberList,
            filter.statuses
        )

    fun signConversionTransfer(transferId: String) =
        networkApiClient.signConversionTransfer(
            transferId
        )

    fun cancelConversionTransfer(transferId: String) =
        networkApiClient.cancelConversionTransfer(
            transferId
        )

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

    fun getCardIssuePrice(cardAccountOwnerId: Int, cardOwnerId: Int) =
        networkApiClient.getCardIssuePrice(
            cardAccountOwnerId,
            cardOwnerId
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

    fun getAuthorizations(filter: AuthorizationFilter): Deferred<MetadataAwareResponse<Authorization>> {
        return networkApiClient.getAuthorizations(
            filter.accountNumbers,
            filter.validFrom?.time,
            filter.validTo?.time,
            filter.limit,
            filter.offset,
            filter.orderBy,
            filter.orderDirectionBy,
            filter.replacedAuthorizationIds
        )
    }

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

    fun getPaymentCardDeliveryPreference(accountNumber: String) =
        networkApiClient.getPaymentCardDeliveryPreference(
            accountNumber
        )

    fun setPaymentCardDeliveryPreference(accountNumber: String, paymentCardDelivery: PaymentCardDelivery) =
        networkApiClient.setPaymentCardDeliveryPreference(
            accountNumber,
            paymentCardDelivery
        )

    fun getPaymentCardExpiringOrderRestriction(accountNumber: String) =
        networkApiClient.getPaymentCardExpiringOrderRestriction(
            accountNumber
        )

    fun getUserSigningLimits(userId: Int) = networkApiClient.getUserSigningLimits(userId)

    fun getAuthorizationUserValidations(body: AuthorizationUserValidationRequest) =
        networkApiClient.getAuthorizationUserValidations(
            body
        )

    fun getCardOrderUserRestrictions(cardAccountOwnerId: Int, cardOwnerId: Int) =
        networkApiClient.getCardOrderUserRestrictions(
            cardAccountOwnerId,
            cardOwnerId
        )

    fun getBullionOptions(filter: BaseFilter) =
        networkApiClient.getBullionOptions(
            filter.limit,
            filter.offset,
            filter.orderBy,
            filter.orderDirection
        )

    fun getBullionItems(filter: BullionFilter) =
        networkApiClient.getBullionItems(
            filter.accountNumber,
            filter.limit,
            filter.offset,
            filter.orderBy,
            filter.orderDirection
        )

    fun getUnallocatedBullionBalance(filter: BullionFilter) =
        networkApiClient.getUnallocatedBullionBalance(
            filter.accountNumber,
            filter.limit,
            filter.offset,
            filter.orderBy,
            filter.orderDirection
        )

    fun buyBullion(request: BuyBullionItemRequest) =
        networkApiClient.buyBullion(
            request
        )

    fun sellBullion(request: SellBullionItemRequest) =
        networkApiClient.sellBullion(
            request
        )

    fun getBullionSpreadPercentage(request: BullionSpreadPercentageRequest) =
        networkApiClient.getBullionSpreadPercentage(
            request.accountNumber,
            request.fromCurrency,
            request.toCurrency,
            request.toAmount
        )

    fun getBankParticipationInformation(swift: String) =
        networkApiClient.getBankParticipationInformation(swift)

    fun getClientAllowances() = networkApiClient.getClientAllowances()

    fun unblockCardCvv(cardId: String) = networkApiClient.unblockCvv(cardId)

    fun getInformationRequests(filter: PSInformationRequestFilter) =
        networkApiClient.getInformationRequests(
            filter.transferId,
            filter.accountNumbers,
            filter.status,
            filter.internalCommentRequired,
            filter.limit,
            filter.offset,
            filter.orderBy,
            filter.orderDirection,
            filter.after,
            filter.before
        )

    fun getInformationRequest(informationRequestId: String) =
        networkApiClient.getInformationRequest(
            informationRequestId
        )

    fun createInformationRequest(informationRequest: PSInformationRequest) =
        networkApiClient.createInformationRequest(
            informationRequest
        )

    fun uploadInformationRequestFile(
        informationRequestId: String,
        file: PSInformationRequestFile
    ) = networkApiClient.uploadInformationRequestFile(
        informationRequestId,
        file
    )

    fun answerInformationRequestQuestions(
        informationRequestId: String,
        answers: PSInformationRequestAnswers
    ) = networkApiClient.answerInformationRequestQuestions(
        informationRequestId,
        answers
    )
}