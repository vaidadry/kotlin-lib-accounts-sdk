package com.paysera.lib.accounts.clients

import com.paysera.lib.accounts.entities.CardLimit
import com.paysera.lib.accounts.entities.cards.Card
import com.paysera.lib.accounts.entities.cards.CardCvv2
import com.paysera.lib.accounts.entities.cards.CardsFilter
import com.paysera.lib.accounts.entities.cards.CreatePaymentCardRequest
import com.paysera.lib.accounts.interfaces.TokenRefresherInterface
import com.paysera.lib.accounts.retrofit.APIClient
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.util.concurrent.TimeUnit.SECONDS

class AccountsApiClient(
    private val apiClient: APIClient,
    private val tokenRefresherInterface: TokenRefresherInterface
) {
    private val retryCondition = { errors: Flowable<Throwable> ->
        errors.flatMap {
            val isUnauthorized = (it as HttpException).code() == 401
            if (isUnauthorized) {
                synchronized(tokenRefresherInterface) {
                    if (tokenRefresherInterface.isRefreshing()) {
                        Flowable.timer(1, SECONDS).subscribeOn(Schedulers.io())
                    } else {
                        tokenRefresherInterface.refreshToken().toFlowable()
                    }
                }
            } else {
                Flowable.error(it)
            }
        }
    }

    fun getIbanInformation(iban: String) =
        apiClient.getIbanInformation(iban).retryWhen(retryCondition)

    fun getFullBalance(accountNumber: String, showHistoricalCurrencies: Boolean = false) =
        apiClient.getFullBalances(accountNumber, if (showHistoricalCurrencies) 1 else 0).retryWhen(retryCondition)

    fun getCards(cardsFilter: CardsFilter) =
        with(cardsFilter) {
            apiClient.getCards(accountNumbers, statuses, cardOwnerId, accountOwnerId).retryWhen(retryCondition)
        }

    fun createCard(card: CreatePaymentCardRequest) =
        apiClient.createCard(card).retryWhen(retryCondition)

    fun activateCard(cardId: String) =
        apiClient.activateCard(cardId).retryWhen(retryCondition)

    fun deactivateCard(cardId: String) =
        apiClient.deactivateCard(cardId).retryWhen(retryCondition)

    fun enableCard(cardId: String) =
        apiClient.enableCard(cardId).retryWhen(retryCondition)

    fun cancelCard(cardId: String) =
        apiClient.cancelCard(cardId).retryWhen(retryCondition)

    fun getCardLimit(accountNumber: String) =
        apiClient.getCardLimit(accountNumber).retryWhen(retryCondition)

    fun setCardLimit(accountNumber: String, cardLimit: CardLimit) =
        apiClient.setCardLimit(accountNumber, cardLimit).retryWhen(retryCondition)

    fun getCardPin(cardId: String, cardCvv2: CardCvv2) =
        apiClient.getCardPin(cardId, cardCvv2).retryWhen(retryCondition)

    fun getCardShippingAddress(accountNumber: String) =
        apiClient.getCardShippingAddress(accountNumber).retryWhen(retryCondition)

    fun getCardDeliveryPrices(country: String) =
        apiClient.getCardDeliveryPrices(country).retryWhen(retryCondition)

    fun getCardIssuePrice(country: String, clientType: String, cardOwnerId: String) =
        apiClient.getCardIssuePrice(country, clientType, cardOwnerId).retryWhen(retryCondition)

    fun getCardDeliveryDate(country: String, deliveryType: String) =
        apiClient.getCardDeliveryDate(country, deliveryType).retryWhen(retryCondition)
}