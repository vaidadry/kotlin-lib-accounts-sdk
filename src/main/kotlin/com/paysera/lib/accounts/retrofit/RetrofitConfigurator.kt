package com.paysera.lib.accounts.retrofit

import com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.paysera.lib.accounts.entities.AccountsApiCredentials
import com.paysera.lib.accounts.entities.Balance
import com.paysera.lib.accounts.entities.CardLimit
import com.paysera.lib.accounts.entities.authorizations.Authorization
import com.paysera.lib.accounts.entities.cards.Card
import com.paysera.lib.accounts.entities.cards.CardPin
import com.paysera.lib.accounts.entities.cards.CategorizedAccountNumbers
import com.paysera.lib.accounts.entities.common.MetadataAwareResponse
import com.paysera.lib.accounts.entities.cards.PaymentCardDesign
import com.paysera.lib.accounts.entities.transfers.TransferNotification
import com.paysera.lib.accounts.serializers.*
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import org.joda.money.Money
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

class RetrofitConfigurator(private val accountsApiCredentials: AccountsApiCredentials, private val timeout: Long? = null) {

    fun createRetrofit(baseUrl: String = "https://accounts.paysera.com/public/") = with(Retrofit.Builder()) {
        baseUrl(baseUrl)
        addConverterFactory(createGsonConverterFactory())
        addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        client(createOkHttpClient())
        build()
    }

    private fun createOkHttpClient() = with(OkHttpClient().newBuilder()) {
        timeout?.let {
            writeTimeout(it, TimeUnit.MILLISECONDS)
            readTimeout(it, TimeUnit.MILLISECONDS)
            connectTimeout(it, TimeUnit.MILLISECONDS)
        }
        addInterceptor { chain ->
            val originalRequest = chain.request()
            val builder =
                originalRequest.newBuilder().header("Authorization", "Bearer ${accountsApiCredentials.accessToken}")
            val modifiedRequest = builder.build()
            chain.proceed(modifiedRequest)
        }
        build()
    }

    private fun createGsonConverterFactory(): GsonConverterFactory {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(LOWER_CASE_WITH_UNDERSCORES)
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

        object : TypeToken<MetadataAwareResponse<String>>() { }.type.apply {
            gsonBuilder.registerTypeAdapter(this, MetadataAwareResponseDeserializer(String::class.java))
        }
        object : TypeToken<MetadataAwareResponse<PaymentCardDesign>>() { }.type.apply {
            gsonBuilder.registerTypeAdapter(this, MetadataAwareResponseDeserializer(PaymentCardDesign::class.java))
        }

        return GsonConverterFactory.create(gsonBuilder.create())
    }
}