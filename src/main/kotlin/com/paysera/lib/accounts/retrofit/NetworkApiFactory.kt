package com.paysera.lib.accounts.retrofit

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.paysera.lib.accounts.clients.AccountsApiClient
import com.paysera.lib.accounts.entities.Balance
import com.paysera.lib.accounts.entities.CardLimit
import com.paysera.lib.accounts.entities.authorizations.Authorization
import com.paysera.lib.accounts.entities.cards.Card
import com.paysera.lib.accounts.entities.cards.CardPin
import com.paysera.lib.accounts.entities.cards.CategorizedAccountNumbers
import com.paysera.lib.accounts.entities.cards.PaymentCardDesign
import com.paysera.lib.accounts.entities.transfers.TransferNotification
import com.paysera.lib.accounts.serializers.*
import com.paysera.lib.common.entities.ApiCredentials
import com.paysera.lib.common.entities.MetadataAwareResponse
import com.paysera.lib.common.interfaces.TokenRefresherInterface
import com.paysera.lib.common.retrofit.BaseApiFactory
import okhttp3.logging.HttpLoggingInterceptor
import org.joda.money.Money
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class NetworkApiFactory(
    userAgent: String?,
    credentials: ApiCredentials,
    timeout: Long? = null,
    httpLoggingInterceptorLevel: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.BASIC
) : BaseApiFactory<AccountsApiClient>(
    userAgent,
    credentials,
    timeout,
    httpLoggingInterceptorLevel
) {
    override fun createClient(baseUrl: String, tokenRefresher: TokenRefresherInterface?): AccountsApiClient {
        createRetrofit(baseUrl, tokenRefresher).apply {
            return AccountsApiClient(
                retrofit.create(NetworkApiClient::class.java),
                apiRequestManager
            )
        }
    }

    override fun createGsonConverterFactory(): GsonConverterFactory {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        val balancesType = TypeToken.getParameterized(List::class.java, Balance::class.java).type
        val cardsType = TypeToken.getParameterized(List::class.java, Card::class.java).type
        val categorizedAccountNumbersType = TypeToken.getParameterized(List::class.java, CategorizedAccountNumbers::class.java).type
        val authorizationsType = TypeToken.getParameterized(List::class.java, Authorization::class.java).type

        gsonBuilder.registerTypeAdapter(balancesType, BalanceDeserializer())
        gsonBuilder.registerTypeAdapter(cardsType, CardsDeserializer())
        gsonBuilder.registerTypeAdapter(categorizedAccountNumbersType, CategorizedAccountNumbersDeserializer())
        gsonBuilder.registerTypeAdapter(authorizationsType, AuthorizationsDeserializer())
        gsonBuilder.registerTypeAdapter(Money::class.java, MoneyDeserializer())
        gsonBuilder.registerTypeAdapter(CardPin::class.java, CardPinDeserializer())
        gsonBuilder.registerTypeAdapter(CardLimit::class.java, CardLimitSerializer())
        gsonBuilder.registerTypeAdapter(TransferNotification::class.java, TransferNotificationDeserializer())
        gsonBuilder.registerTypeAdapter(Date::class.java, DateSerializer())

        object : TypeToken<MetadataAwareResponse<Authorization>>() {}.type.apply {
            gsonBuilder.registerTypeAdapter(this, MetadataAwareResponseDeserializer(Authorization::class.java))
        }
        object : TypeToken<MetadataAwareResponse<String>>() {}.type.apply {
            gsonBuilder.registerTypeAdapter(this, MetadataAwareResponseDeserializer(String::class.java))
        }
        object : TypeToken<MetadataAwareResponse<PaymentCardDesign>>() {}.type.apply {
            gsonBuilder.registerTypeAdapter(this, MetadataAwareResponseDeserializer(PaymentCardDesign::class.java))
        }

        return GsonConverterFactory.create(gsonBuilder.create())
    }
}