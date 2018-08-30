package com.paysera.lib.accounts.clients

import com.paysera.lib.accounts.entities.Balance
import com.paysera.lib.accounts.entities.IbanInformation
import com.paysera.lib.accounts.entities.CardLimit
import com.paysera.lib.accounts.entities.cards.Card
import com.paysera.lib.accounts.entities.cards.CardsFilter
import com.paysera.lib.accounts.entities.cards.CardCvv2
import com.paysera.lib.accounts.entities.cards.CardPin
import com.paysera.lib.accounts.interfaces.TokenRefresherInterface
import io.reactivex.schedulers.Schedulers
import com.paysera.lib.accounts.retrofit.APIClient
import io.reactivex.Flowable
import io.reactivex.Single
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

    fun
        getIbanInformation(iban: String): Single<IbanInformation> {
        return apiClient.getIbanInformation(iban).retryWhen(retryCondition)
    }

    fun getFullBalance(accountNumber: String, showHistoricalCurrencies: Boolean = false): Single<List<Balance>> {
        return apiClient.getFullBalances(accountNumber, if (showHistoricalCurrencies) 1 else 0).retryWhen(retryCondition)
    }

    fun getCards(cardsFilter: CardsFilter): Single<List<Card>> {
        return with(cardsFilter) {
            apiClient.getCards(accountNumbers, statuses, cardOwnerId, accountOwnerId).retryWhen(retryCondition)
        }
    }

    fun createCard(card: Card): Single<Card> {
        return apiClient.createCard(card).retryWhen(retryCondition)
    }

    fun activateCard(cardId: String): Single<Card> {
        return apiClient.activateCard(cardId).retryWhen(retryCondition)
    }

    fun deactivateCard(cardId: String): Single<Card> {
        return apiClient.deactivateCard(cardId).retryWhen(retryCondition)
    }

    fun enableCard(cardId: String): Single<Card> {
        return apiClient.enableCard(cardId).retryWhen(retryCondition)
    }

    fun cancelCard(cardId: String): Single<Card> {
        return apiClient.cancelCard(cardId).retryWhen(retryCondition)
    }

    fun getCardLimit(accountNumber: String): Single<CardLimit> {
        return apiClient.getCardLimit(accountNumber).retryWhen(retryCondition)
    }

    fun setCardLimit(accountNumber: String, cardLimit: CardLimit): Single<CardLimit> {
        return apiClient.setCardLimit(accountNumber, cardLimit).retryWhen(retryCondition)
    }

    fun getCardPin(cardId: String, cardCvv2: CardCvv2): Single<CardPin> {
        return apiClient.getCardPin(cardId, cardCvv2).retryWhen(retryCondition)
    }
}